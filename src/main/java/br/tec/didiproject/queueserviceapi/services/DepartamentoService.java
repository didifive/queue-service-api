package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.AtendenteRepository;
import br.tec.didiproject.queueserviceapi.repositories.DepartamentoRepository;
import br.tec.didiproject.queueserviceapi.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final AtendenteRepository atendenteRepository;

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
     * @param departamentoId UUID with the id of the existing department
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

        Set<Atendente> atendentes = atendenteRepository.findAllByDepartamentosIdContains(departamento.getId());
        if (!atendentes.isEmpty())
            throw new DataIntegrityViolationException(
                    DEPARTMENT_WITH_ASSOCIATED_ATTENDANT
                            .params(
                                    departamentoId.toString()
                                    , atendentes.stream()
                                            .map(Atendente::getId)
                                            .map(UUID::toString)
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        departamentoRepository.delete(departamento);
    }

}