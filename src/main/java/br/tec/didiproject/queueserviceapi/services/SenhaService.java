package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.*;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class SenhaService {

    public static final String MOTIVO_SENHA_ATENDIDA = "Senha atendida por [%s] com Id [%s].";
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
    public Page<Senha> senhasNaoChamadasPorFilaEPorTipoAtendimento(UUID filaId, UUID tipoAtendimento, Pageable pageable) {
        return senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullOrderByGeradaEmAsc(
                filaId
                , tipoAtendimento
                , pageable);
    }

    /**
     * CRUD: Read
     * Find service numbers called and not finished by queue and attendance type and list with pageable content
     *
     * @param filaId Pageable object with page options
     * @param tipoAtendimento Pageable object with page options
     * @param pageable Pageable object with page options
     */
    public Page<Senha> senhasChamadasENaoFinalizadasPorFilaEPorTipoAtendimento(UUID filaId, UUID tipoAtendimento, Pageable pageable) {
        return senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndChamadaEmIsNotNullOrderByGeradaEmDesc(
                filaId
                , tipoAtendimento
                , pageable);
    }


    /**
     * CRUD: Create
     * Create a new service number
     *
     * @param filaId  UUID with a queue id
     * @param tipoAtendimentoId UUID with a attendance type id
     */
    public Senha create(UUID filaId, UUID tipoAtendimentoId) {
        Fila fila = filaService.findById(filaId);
        TipoAtendimento tipoAtendimento = tipoAtendimentoService.findById(tipoAtendimentoId);

        Senha ultimaSenha = senhaRepository
                .findFirstByFilaIdAndTipoAtendimentoIdOrderByNumeroDesc(filaId
                        , tipoAtendimentoId)
                .orElse(Senha.builder().numero(Short.MAX_VALUE).build());
        Short ultimoNumero = ultimaSenha.getNumero().equals(Short.MAX_VALUE)
                ? 0
                : ultimaSenha.getNumero();

        ultimoNumero++;

        Timestamp agora = getNow();

        Senha novaSenha = Senha.builder()
                .numero(ultimoNumero)
                .fila(fila)
                .tipoAtendimento(tipoAtendimento)
                .geradaEm(agora)
                .build();

        return senhaRepository.save(novaSenha);
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
    public Senha chamaSenha(UUID senhaId, boolean rechamada) {
        Senha senha = this.findById(senhaId);

        if (senha.foiChamada() && !rechamada){
            throw new DataIntegrityViolationException(
                    SERVICE_NUMBER_ALREADY_CALLED
                            .params(senhaId.toString()).getMessage()
            );
        }

        senha.setChamadaEm(getNow());

        return senhaRepository.save(senha);
    }

    /**
     * CRUD: Update
     * Finish answering the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param motivo String with a reason for finish the service number
     */
    public Senha finalizaSenha(UUID senhaId, String motivo) {
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
     * Answering the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param usuario Usuario object with the user of attendant
     */
    public Senha atendeSenha(UUID senhaId, Usuario usuario) {
        Senha senha = this.findById(senhaId);
        Atendente atendente = atendenteService.findById(usuario.getAtendente().getId());

        if (senha.foiAtendida()) {
            throw new DataIntegrityViolationException(
                    SERVICE_NUMBER_ALREADY_ANSWERED
                            .params(senhaId.toString()).getMessage()
            );
        }

        senha = finalizaSenha(senha.getId()
                , String.format(MOTIVO_SENHA_ATENDIDA,atendente.getNome(),atendente.getId().toString()));

        senha.setAtendidaEm(senha.getFinalizadaEm());
        senha.setAtendente(atendente);

        return senhaRepository.save(senha);
    }

    /**
     * CRUD: Update
     * Rollback the service number to the Queue
     *
     * @param senhaId UUID with the id of the existing service number
     */
    public Senha voltarSenhaParaFila(UUID senhaId) {
        Senha senha = this.findById(senhaId);

        senha.setFinalizadaEm(null);
        senha.setMotivoFinalizada(null);
        senha.setAtendidaEm(null);
        senha.setAtendente(null);

        return senhaRepository.save(senha);
    }

}