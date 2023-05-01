package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.exceptions.QueueServiceApiException;
import br.tec.didiproject.queueserviceapi.repositories.FilaRepository;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import br.tec.didiproject.queueserviceapi.repositories.TipoAtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class TipoAtendimentoService {

    private final TipoAtendimentoRepository tipoAtendimentoRepository;
    private final FilaRepository filaRepository;
    private final SenhaRepository senhaRepository;

    /**
     * CRUD: Read
     * Find attendance type by id
     *
     * @param tipoAtendimentoId UUID with the attendance type id
     */
    public TipoAtendimento findById(UUID tipoAtendimentoId) {
        return tipoAtendimentoRepository.findById(tipoAtendimentoId).orElseThrow(
                () -> new EntityNotFoundException(
                        ATTENDANCE_TYPE_NOT_FOUND.params(tipoAtendimentoId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find attendances type and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<TipoAtendimento> findAll(Pageable pageable) {
        return tipoAtendimentoRepository.findAll(pageable);
    }

    /**
     * CRUD: Create
     * Create a new attendance type
     *
     * @param tipoAtendimento TipoAtendimento object with the new attendance type  data
     */
    public TipoAtendimento create(TipoAtendimento tipoAtendimento) {
        return this.trySaveAttendanceType(tipoAtendimento);
    }

    private TipoAtendimento trySaveAttendanceType(TipoAtendimento tipoAtendimento) {
        try {
            tipoAtendimento = tipoAtendimentoRepository.save(tipoAtendimento);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            Pattern pattern = Pattern.compile(tipoAtendimento.getSigla(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(e.getCause().getCause().getMessage());
            if (matcher.find())
                throw new DataIntegrityViolationException(
                        ATTENDANCE_TYPE_WITH_DUPLICATE_ABBREVIATION
                                .params(tipoAtendimento.getSigla())
                                .getMessage());
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        } catch (Exception e) {
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        }

        return tipoAtendimento;
    }

    /**
     * CRUD: Update
     * Update attendance type data
     *
     * @param tipoAtendimentoId   UUID with the id of the existing attendance type
     * @param novoTipoAtendimento TipoAtendimento object with a new attendance type data
     */
    public TipoAtendimento atualizarTipoAtendimento(UUID tipoAtendimentoId, TipoAtendimento novoTipoAtendimento) {
        this.findById(tipoAtendimentoId);

        novoTipoAtendimento.setId(tipoAtendimentoId);

        return this.trySaveAttendanceType(novoTipoAtendimento);
    }

    /**
     * CRUD: Delete
     * Delete the company
     *
     * @param tipoAtendimentoId UUID with the id of the existing company
     */
    public void deletarTipoAtendimento(UUID tipoAtendimentoId) {
        TipoAtendimento tipoAtendimento = this.findById(tipoAtendimentoId);

        Pageable pageRequest = PageRequest.of(0, 10);

        Page<Fila> filas = filaRepository.findAllByTiposAtendimentoId(tipoAtendimentoId, pageRequest);
        if (filas.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    ATTENDANCE_TYPE_WITH_ASSOCIATED_QUEUE
                            .params(
                                    tipoAtendimentoId.toString()
                                    , filas.stream()
                                            .map(f -> f.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        Page<Senha> senhas = senhaRepository.findAllByTipoAtendimentoId(tipoAtendimentoId, pageRequest);
        if (senhas.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    ATTENDANCE_TYPE_WITH_ASSOCIATED_SERVICE
                            .params(
                                    tipoAtendimentoId.toString()
                                    , senhas.stream()
                                            .map(s -> s.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        tipoAtendimentoRepository.delete(tipoAtendimento);
    }

}