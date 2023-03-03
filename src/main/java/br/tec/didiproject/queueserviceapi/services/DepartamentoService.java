package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.AtendenteRepository;
import br.tec.didiproject.queueserviceapi.repositories.DepartamentoRepository;
import br.tec.didiproject.queueserviceapi.repositories.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final AtendenteRepository atendenteRepository;
    private final FilaRepository filaRepository;

    /**
     * CRUD: Read
     * Find department by id
     *
     * @param departamentoId UUID with the department id
     */
    public Departamento findById(UUID departamentoId) {
        return departamentoRepository.findById(departamentoId).orElseThrow(
                () -> new EntityNotFoundException(
                        DEPARTMENT_NOT_FOUND.params(departamentoId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find departments and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Departamento> findAll(Pageable pageable) {
        return departamentoRepository.findAll(pageable);
    }

    /**
     * CRUD: Create
     * Create a new department
     *
     * @param novoDepartamento Departamento object with the new company data
     */
    public Departamento create(Departamento novoDepartamento) {
        return departamentoRepository.save(novoDepartamento);
    }

    /**
     * CRUD: Update
     * Update department data
     *
     * @param departamentoId   UUID with the id of the existing department
     * @param novoDepartamento Departamento object with a new department data
     */
    public Departamento atualizarDepartamento(UUID departamentoId, Departamento novoDepartamento) {
        Departamento departamentoExistente = this.findById(departamentoId);

        novoDepartamento.setId(departamentoExistente.getId());

        return departamentoRepository.save(novoDepartamento);
    }

    /**
     * CRUD: Delete
     * Delete the department
     *
     * @param departamentoId UUID with the id of the existing department
     */
    public void deletarDepartamento(UUID departamentoId) {
        Departamento departamento = this.findById(departamentoId);

        Pageable pageRequest = PageRequest.of(0, 10);

        Page<Atendente> atendentes = atendenteRepository.findAllByDepartamentosIdContains(departamento.getId(), pageRequest);
        if (atendentes.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    DEPARTMENT_WITH_ASSOCIATED_ATTENDANT
                            .params(
                                    departamentoId.toString()
                                    , atendentes.stream()
                                            .map(a -> a.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        Page<Fila> filas = filaRepository.findAllByDepartamentoId(departamento.getId(), pageRequest);
        if (filas.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    DEPARTMENT_WITH_ASSOCIATED_QUEUE
                            .params(
                                    departamentoId.toString()
                                    , filas.stream()
                                            .map(f -> f.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        departamentoRepository.delete(departamento);
    }

}