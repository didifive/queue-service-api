package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.*;
import br.tec.didiproject.queueserviceapi.enums.AdjustTimeTo;
import br.tec.didiproject.queueserviceapi.exceptions.*;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static br.tec.didiproject.queueserviceapi.enums.AdjustTimeTo.END;
import static br.tec.didiproject.queueserviceapi.enums.AdjustTimeTo.START;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;
import static java.time.LocalTime.of;

@RequiredArgsConstructor
@Service
public class SenhaService {

    public static final String MOTIVO_SENHA_ATENDIDA = "Senha atendida pelo Usuário/Atendente Id [%s] - Nome [%s]";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    private static final Integer PAGE_SIZE = 10;
    private final SenhaRepository senhaRepository;
    private final FilaService filaService;
    private final TipoAtendimentoService tipoAtendimentoService;
    private final AtendenteService atendenteService;

    private static Timestamp getNow() {
        Date hoje = new Date();
        return new Timestamp(hoje.getTime());
    }

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
     * Find service numbers by Creation date interval
     *
     * @param dataInicio String with the start day for filtering
     * @param dataFim    String with the last day for filtering
     * @param pageable   Pageable object with page options
     */
    public Page<Senha> senhasPorIntervaloDataDeGeracao(String dataInicio, String dataFim, Pageable pageable) {
        List<Date> datas = this.transformaDatas(dataInicio, dataFim);

        return senhaRepository.findAllByGeradaEmBetween(
                this.ajustarTempo(datas.get(0), START)
                , this.ajustarTempo(datas.get(1), END)
                , pageable);
    }

    /**
     * CRUD: Read
     * Find service numbers by call date interval
     *
     * @param dataInicio String with the start day for filtering
     * @param dataFim    String with the last day for filtering
     * @param pageable   Pageable object with page options
     */
    public Page<Senha> senhasChamadasPorIntervaloData(String dataInicio, String dataFim, Pageable pageable) {
        List<Date> datas = this.transformaDatas(dataInicio, dataFim);

        return senhaRepository.findAllByChamadaEmBetween(
                this.ajustarTempo(datas.get(0), START)
                , this.ajustarTempo(datas.get(1), END)
                , pageable);
    }

    /**
     * CRUD: Read
     * Find service numbers by finalized date interval
     *
     * @param dataInicio String with the start day for filtering
     * @param dataFim    String with the last day for filtering
     * @param pageable   Pageable object with page options
     */
    public Page<Senha> senhasFinalizadasPorIntervaloData(String dataInicio, String dataFim, Pageable pageable) {

        List<Date> datas = this.transformaDatas(dataInicio, dataFim);

        return senhaRepository.findAllByFinalizadaEmBetween(
                this.ajustarTempo(datas.get(0), START)
                , this.ajustarTempo(datas.get(1), END)
                , pageable);
    }

    /**
     * CRUD: Read
     * Find service numbers by attendance date interval
     *
     * @param dataInicio String with the start day for filtering
     * @param dataFim    String with the last day for filtering
     * @param pageable   Pageable object with page options
     */
    public Page<Senha> senhasAtendidasPorIntervaloData(String dataInicio, String dataFim, Pageable pageable) {
        List<Date> datas = this.transformaDatas(dataInicio, dataFim);

        return senhaRepository.findAllByAtendidaEmBetween(
                this.ajustarTempo(datas.get(0), START)
                , this.ajustarTempo(datas.get(1), END)
                , pageable);
    }

    private List<Date> transformaDatas(String dataInicio, String dataFim) {
        Date dataTempoInicio = this.convertDate(dataInicio);
        Date dataTempoFim = this.convertDate(dataFim);

        List<Date> datas = new ArrayList<>();
        datas.add(dataTempoInicio);
        datas.add(dataTempoFim);
        Collections.sort(datas);

        return datas;
    }

    private Date convertDate(String dataString) {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
        var data = LocalDate.now();
        try {
            data = LocalDate.parse(dataString, datePattern);
        } catch (DateTimeParseException e) {
            throw new BadRequestBodyException(
                    GENERIC_DATE_STRING_IS_NOT_VALID
                            .params(dataString)
                            .getMessage()
            );
        } catch (Exception e) {
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        }
        return Date.from(data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date ajustarTempo(Date data, AdjustTimeTo adjustTimeTo) {
        var hora = of(0, 0, 0, 0);
        if (adjustTimeTo == END) {
            hora = of(23, 59, 59, 999999999);
        }
        LocalDateTime localDateTime = LocalDateTime.of(
                Instant.ofEpochMilli(data.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                , hora);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
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
     * CRUD: Create
     * Create a new service number
     *
     * @param filaId            UUID with a queue id
     * @param tipoAtendimentoId UUID with a attendance type id
     * @param reset             Short with a number for reset sequence number for service
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

    public void create(UUID filaId, UUID tipoAtendimentoId) {
        this.create(filaId, tipoAtendimentoId, null);
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

    /**
     * CRUD: Update
     * Call the service number
     *
     * @param senhaId   UUID with the id of the existing service number
     * @param rechamada boolean to verify if the service number is to be called again
     */
    public Senha chamarSenha(UUID senhaId, Boolean rechamada) {
        Senha senha = this.findById(senhaId);

        this.checkSenhaFinalizada(senha);
        boolean naoRechamada = Objects.isNull(rechamada) || !rechamada;
        boolean senhaChamada = senha.foiChamada() && naoRechamada;
        if (senhaChamada) {
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

        return this.chamarSenha(this.getPrimeiraSenha(fila).getId(), false);
    }

    /**
     * Check first service numbers for each attendance types of a queue
     *
     * @param fila Fila object
     * @return Return a Senha object if exists a service number to call in the queue
     * @apiNote The "senhas" variable is a "TreeSet" object so that the service number, when exists and included in the "Set",
     * is organized according to the priority defined in the "compareTo" method of the "Senha" object
     */
    private Senha getPrimeiraSenha(Fila fila) {
        Set<Senha> senhas = new TreeSet<>();
        fila.getTiposAtendimento().forEach(tipoAtendimento -> {
            Optional<Senha> senha = senhaRepository
                    .findFirstByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullAndFinalizadaEmIsNull(
                            fila.getId()
                            , tipoAtendimento.getId()
                    );
            senha.ifPresent(senhas::add);
        });

        if (senhas.isEmpty())
            throw new BadRequestBodyException(
                    QUEUE_NOT_CONTAINS_SERVICE_NUMBER_TO_CALL
                            .params(fila.getId().toString())
                            .getMessage()
            );

        return senhas.iterator().next();
    }

    /**
     * CRUD: Update
     * Finish answering the service number
     *
     * @param senhaId UUID with the id of the existing service number
     * @param motivo  String with a reason for finish the service number
     */
    public Senha finalizarSenha(UUID senhaId, String motivo) {
        Senha senha = this.findById(senhaId);

        this.checkSenhaFinalizada(senha);

        senha.setFinalizadaEm(getNow());
        senha.setMotivoFinalizada(motivo);

        return senhaRepository.saveAndFlush(senha);
    }

    private void checkSenhaFinalizada(Senha senha) {
        if (senha.foiFinalizada()) {
            throw new BadRequestBodyException(
                    SERVICE_NUMBER_ALREADY_FINISHED
                            .params(senha.getId().toString()).getMessage()
            );
        }
    }

    /**
     * CRUD: Update
     * Finish answering the service number for queue and attendance type
     *
     * @param filaId            UUID with the id of the existing queue
     * @param tipoAtendimentoId UUID with the id of the existing attendance type
     * @param motivo            String with a reason for finish the service number
     */
    public void finalizarSenhaPorFilaETipoAtendimento(UUID filaId, UUID tipoAtendimentoId, String motivo) {
        Pageable pageRequest = PageRequest.of(0, PAGE_SIZE);

        Page<Senha> senhas = senhaRepository.findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndMotivoFinalizadaIsNull(
                filaId
                , tipoAtendimentoId
                , pageRequest);

        for (int i = 0; i <= senhas.getTotalPages(); i++) {
            pageRequest = PageRequest.of(i, PAGE_SIZE);
            Page<Senha> pageSenhasParaFinalizar = senhaRepository
                    .findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndMotivoFinalizadaIsNull(
                            filaId
                            , tipoAtendimentoId
                            , pageRequest);
            pageSenhasParaFinalizar.getContent().forEach(senha -> this.finalizarSenha(senha.getId(), motivo));
        }
    }

    /**
     * CRUD: Update
     * Finish the service numbers who is not finished
     *
     * @param motivo String with a reason for finish the service number
     */
    public void finalizarSenhasNaoFinalizadas(String motivo) {
        Pageable pageRequest = PageRequest.of(0, PAGE_SIZE);

        Page<Senha> senhas = this.senhasNaoFinalizadas(pageRequest);

        for (int i = 0; i <= senhas.getTotalPages(); i++) {
            pageRequest = PageRequest.of(i, PAGE_SIZE);
            Page<Senha> pageSenhasParaFinalizar = this.senhasNaoFinalizadas(pageRequest);
            pageSenhasParaFinalizar.getContent().forEach(senha -> this.finalizarSenha(senha.getId(), motivo));
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
                , String.format(MOTIVO_SENHA_ATENDIDA, atendente.getId().toString(), atendente.getNome()));

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