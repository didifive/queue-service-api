package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.*;
import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class SenhaService {

    public static final String MOTIVO_SENHA_ATENDIDA = "Senha atendida pelo Usuário/Atendente Id [%s] - Nome [%s]";
    private static final Integer PAGE_SIZE = 10;
    private final SenhaRepository senhaRepository;
    private final FilaService filaService;
    private final TipoAtendimentoService tipoAtendimentoService;
    private final AtendenteService atendenteService;

    /**
     * CRUD: Read
     * Find service number by id
     *
     * @param senhaId UUID with the queue id
     */
    public Senha findById(UUID senhaId) {
        return senhaRepository.findById(senhaId).orElseThrow(
                () -> new EntityNotFoundException(SERVICE_NUMBER_NOT_FOUND
                        .params(senhaId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find services numbers and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Senha> findAll(Pageable pageable) {
        return senhaRepository.findAll(pageable);
    }

    /**
     * CRUD: Read
     * Find unfinished service numbers and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Senha> senhasNaoFinalizadas(Pageable pageable) {
        return senhaRepository.findAllByFinalizadaEmIsNull(pageable);
    }

    /**
     * CRUD: Read
     * Find service numbers not called by queue and attendance type and list with pageable content
     *
     * @param filaId Pageable object with page options
     * @param tipoAtendimento Pageable object with page options
     * @param pageable Pageable object with page options
     */
//    public Page<Senha> senhasNaoChamadasPorFilaEPorTipoAtendimento(UUID filaId, UUID tipoAtendimento, Pageable pageable) {
//        return senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullOrderByGeradaEmAsc(
//                filaId
//                , tipoAtendimento
//                , pageable);
//    }

    /**
     * CRUD: Read
     * Find service numbers called and not finished by queue and attendance type and list with pageable content
     *
     * @param filaId Pageable object with page options
     * @param tipoAtendimento Pageable object with page options
     * @param pageable Pageable object with page options
     */
//    public Page<Senha> senhasChamadasENaoFinalizadasPorFilaEPorTipoAtendimento(UUID filaId, UUID tipoAtendimento, Pageable pageable) {
//        return senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndChamadaEmIsNotNullOrderByGeradaEmDesc(
//                filaId
//                , tipoAtendimento
//                , pageable);
//    }


    /**
     * CRUD: Create
     * Create a new service number
     *
     * @param filaId  UUID with a queue id
     * @param tipoAtendimentoId UUID with a attendance type id
     * @param reset Short with a number for reset sequence number for service
     */
    public Senha create(UUID filaId, UUID tipoAtendimentoId, Short reset) {
        Fila fila = filaService.findById(filaId);
        TipoAtendimento tipoAtendimento = tipoAtendimentoService.findById(tipoAtendimentoId);

        this.checkQueueAndAttendanceTypeLink(fila, tipoAtendimento);

        Short newNumber = Objects.isNull(reset) ? this.newNumber(filaId) : reset;

        Timestamp agora = getNow();

        Senha novaSenha = Senha.builder()
                .numero(newNumber)
                .fila(fila)
                .tipoAtendimento(tipoAtendimento)
                .geradaEm(agora)
                .build();

        return senhaRepository.save(novaSenha);
    }
    public Senha create(UUID filaId, UUID tipoAtendimentoId) {
        return this.create(filaId,tipoAtendimentoId,null);
    }

    private Short newNumber(UUID filaId) {
        Senha ultimaSenha = senhaRepository
                .findFirstByFilaIdOrderByGeradaEmDesc(filaId)
                .orElse(Senha.builder().numero(Short.MAX_VALUE).build());
        Short ultimoNumero = ultimaSenha.getNumero().equals(Short.MAX_VALUE)
                ? 0
                : ultimaSenha.getNumero();
        ultimoNumero++;
        return ultimoNumero;
    }

    private void checkQueueAndAttendanceTypeLink(Fila fila, TipoAtendimento tipoAtendimento) {
        if (!fila.getTiposAtendimento().contains(tipoAtendimento))
            throw new DataIntegrityViolationException(
                    QUEUE_NOT_CONTAINS_ATTENDANCE_TYPE
                            .params(fila.getId().toString())
                            .params(tipoAtendimento.getId().toString())
                            .getMessage()
            );
    }

    private static Timestamp getNow() {
        Date hoje = new Date();
        return new Timestamp(hoje.getTime());
    }

    /**
     * CRUD: Update
     * Call the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param rechamada boolean to verify if the service number is to be called again
     */
    public Senha chamaSenha(UUID senhaId, Boolean rechamada) {
        Senha senha = this.findById(senhaId);

        if (senha.foiChamada()
                && (Objects.isNull(rechamada) || !rechamada)){
            throw new BadRequestBodyException(
                    SERVICE_NUMBER_ALREADY_CALLED
                            .params(senhaId.toString()).getMessage()
            );
        }

        senha.setChamadaEm(getNow());

        return senhaRepository.save(senha);
    }

    /**
     * CRUD: Update
     * Call the next service number for queue
     *
     * @param filaId UUID with the id of the queue
     */
    public Senha chamarProximaSenha(UUID filaId) {
        Fila fila = filaService.findById(filaId);

        Set<Senha> senhas = new TreeSet<>();
        fila.getTiposAtendimento().forEach(tipoAtendimento -> {
            Optional<Senha> senha = senhaRepository
                    .findFirstByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullAndFinalizadaEmIsNullOrderByGeradaEmAsc(
                            filaId
                            , tipoAtendimento.getId()
                    );
            senha.ifPresent(senhas::add);
        });

        return this.chamaSenha(senhas.iterator().next().getId(), false);
    }

    /**
     * CRUD: Update
     * Finish answering the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param motivo String with a reason for finish the service number
     */
    public Senha finalizarSenha(UUID senhaId, String motivo) {
        Senha senha = this.findById(senhaId);

        if (senha.foiFinalizada()) {
           throw new DataIntegrityViolationException(
                   SERVICE_NUMBER_ALREADY_FINISHED
                           .params(senhaId.toString()).getMessage()
           );
        }

        senha.setFinalizadaEm(getNow());
        senha.setMotivoFinalizada(motivo);

        return senhaRepository.saveAndFlush(senha);
    }

    /**
     * CRUD: Update
     * Finish answering the service number for queue and attendance type
     *
     * @param filaId UUID with the id of the existing queue
     * @param tipoAtendimentoId UUID with the id of the existing attendance type
     * @param motivo String with a reason for finish the service number
     */
    public void finalizarSenhaPorFilaETipoAtendimento(UUID filaId, UUID tipoAtendimentoId, String motivo) {
        Pageable pageRequest = PageRequest.of(0, PAGE_SIZE);

        Page<Senha> senhas = senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndMotivoFinalizadaIsNull(
                filaId
                , tipoAtendimentoId
                , pageRequest);

        for (int i = 0; i <= senhas.getTotalPages(); i++){
            pageRequest = PageRequest.of(i, PAGE_SIZE);
            Page<Senha> pageSenhasParaFinalizar = senhaRepository
                    .findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndMotivoFinalizadaIsNull(
                            filaId
                            , tipoAtendimentoId
                            , pageRequest);
            pageSenhasParaFinalizar.getContent().forEach(senha -> this.finalizarSenha(senha.getId(),motivo));
        }
    }

    /**
     * CRUD: Update
     * Answering the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param usuario Usuario object with the user of attendant
     */
    public Senha atenderSenha(UUID senhaId, Usuario usuario) {
        Senha senha = this.findById(senhaId);
        Atendente atendente = atendenteService.findById(usuario.getAtendente().getId());

        if (senha.foiAtendida()) {
            throw new DataIntegrityViolationException(
                    SERVICE_NUMBER_ALREADY_ANSWERED
                            .params(senhaId.toString()).getMessage()
            );
        }

        senha = this.finalizarSenha(senha.getId()
                , String.format(MOTIVO_SENHA_ATENDIDA,atendente.getId().toString(),atendente.getNome()));

        senha.setAtendidaEm(senha.getFinalizadaEm());
        senha.setAtendente(atendente);

        return senhaRepository.save(senha);
    }

    /**
     * CRUD: Update
     * Reset service number status - restart call, pick up and close
     *
     * @param senhaId UUID with the id of the existing service number
     */
    public Senha resetarStatusSenha(UUID senhaId) {
        Senha senha = this.findById(senhaId);

        senha.setChamadaEm(null);
        senha.setFinalizadaEm(null);
        senha.setMotivoFinalizada(null);
        senha.setAtendidaEm(null);
        senha.setAtendente(null);

        return senhaRepository.save(senha);
    }
}