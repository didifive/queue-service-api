package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.exceptions.QueueServiceApiException;
import br.tec.didiproject.queueserviceapi.repositories.FilaRepository;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class FilaService {

    private final FilaRepository filaRepository;

    private final SenhaRepository senhaRepository;
    private final TipoAtendimentoService tipoAtendimentoService;

    /**
     * CRUD: Read
     * Find queue by id
     *
     * @param filaId UUID with the queue id
     */
    public Fila findById(UUID filaId) {
        return filaRepository.findById(filaId).orElseThrow(
                () -> new EntityNotFoundException(QUEUE_NOT_FOUND
                        .params(filaId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find queues and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Fila> findAll(Pageable pageable) {
        return filaRepository.findAll(pageable);
    }

    /**
     * CRUD: Read
     * Find queues and list with pageable content
     *
     * @param departamentoId    UUID with the department id
     * @param pageable          Pageable object with page options
     */
    public Page<Fila> findAllByDepartamentoId(UUID departamentoId, Pageable pageable) {
        return filaRepository.findAllByDepartamentoId(departamentoId, pageable);
    }


    /**
     * CRUD: Create
     * Create a new queue
     *
     * @param novaFila Fila object with the new queue data
     */
    public Fila create(Fila novaFila) {
        return this.trySaveQueue(novaFila);
    }

    private Fila trySaveQueue(Fila novaFila) {

        this.hasAttendanceType(novaFila);

        try {
            novaFila = filaRepository.save(novaFila);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            Pattern pattern = Pattern.compile(novaFila.getSigla(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(e.getCause().getCause().getMessage());
            if (matcher.find())
                throw new DataIntegrityViolationException(
                        QUEUE_WITH_DUPLICATE_ABBREVIATION
                                .params(novaFila.getSigla())
                                .getMessage());
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        } catch (Exception e) {
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        }
        return novaFila;
    }

    private void hasAttendanceType(Fila fila) {
        if (fila.getTiposAtendimento().isEmpty())
            throw new BadRequestBodyException(
                    QUEUE_WITHOUT_ATTENDANCE_TYPE
                            .params(fila.getId().toString())
                            .getMessage());
    }

    /**
     * CRUD: Update
     * Update queue
     *
     * @param filaId         UUID with the id of the existing queue
     * @param filaAtualizada Fila object with a new queue data
     */
    public Fila atualizarFila(UUID filaId, Fila filaAtualizada) {
        this.findById(filaId);

        filaAtualizada.setId(filaId);

        return this.trySaveQueue(filaAtualizada);
    }

    /**
     * CRUD: Update
     * Add a Attendance Type to Queue
     *
     * @param filaId            UUID with the queue Id
     * @param tipoAtendimentoId UUID with the attendance type Id
     */
    public Fila adicionarTipoAtendimento(UUID filaId, UUID tipoAtendimentoId) {
        Fila fila = this.findById(filaId);
        Set<TipoAtendimento> tiposAtendimento = new HashSet<>(fila.getTiposAtendimento());

        tiposAtendimento.add(tipoAtendimentoService.findById(tipoAtendimentoId));
        fila.setTiposAtendimento(tiposAtendimento);

        return filaRepository.save(fila);
    }

    /**
     * CRUD: Update
     * Remove a Attendance Type to Queue
     *
     * @param filaId            UUID with the queue Id
     * @param tipoAtendimentoId UUID with the attendance type Id
     */
    public Fila removerTipoAtendimento(UUID filaId, UUID tipoAtendimentoId) {
        Fila fila = this.findById(filaId);
        Set<TipoAtendimento> tiposAtendimento = new HashSet<>(fila.getTiposAtendimento());

        tiposAtendimento.remove(tipoAtendimentoService.findById(tipoAtendimentoId));
        fila.setTiposAtendimento(tiposAtendimento);

        this.hasAttendanceType(fila);

        return filaRepository.save(fila);
    }

    /**
     * CRUD: Delete
     * Delete the queue
     *
     * @param filaId UUID with the id of the existing queue
     */
    public void deletarFila(UUID filaId) {
        Fila fila = this.findById(filaId);

        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Senha> senhas = senhaRepository.findAllByFilaId(filaId, pageRequest);
        if (senhas.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    QUEUE_WITH_ASSOCIATED_SERVICE
                            .params(
                                    filaId.toString()
                                    , senhas.stream()
                                            .map(s -> s.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        filaRepository.delete(fila);
    }

}