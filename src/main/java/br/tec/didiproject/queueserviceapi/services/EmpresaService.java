package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.DepartamentoRepository;
import br.tec.didiproject.queueserviceapi.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.COMPANY_NOT_FOUND;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.COMPANY_WITH_ASSOCIATED_DEPARTMENT;

@RequiredArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final DepartamentoRepository departamentoRepository;

    /**
     * CRUD: Read
     * Find company by id
     *
     * @param empresaId UUID with the company id
     */
    public Empresa findById(UUID empresaId) {
        return empresaRepository.findById(empresaId).orElseThrow(
                () -> new EntityNotFoundException(
                        COMPANY_NOT_FOUND.params(empresaId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find companies and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Empresa> findAll(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    /**
     * CRUD: Create
     * Create a new company
     *
     * @param novaEmpresa Empresa object with the new company data
     */
    public Empresa create(Empresa novaEmpresa) {
        return empresaRepository.save(novaEmpresa);
    }

    /**
     * CRUD: Update
     * Update company data
     *
     * @param empresaId   UUID with the id of the existing company
     * @param novaEmpresa Empresa object with a new company data
     */
    public Empresa atualizarEmpresa(UUID empresaId, Empresa novaEmpresa) {
        Empresa empresaExistente = this.findById(empresaId);

        novaEmpresa.setId(empresaExistente.getId());

        return empresaRepository.save(novaEmpresa);
    }

    /**
     * CRUD: Delete
     * Delete the company
     *
     * @param empresaId UUID with the id of the existing company
     */
    public void deletarEmpresa(UUID empresaId) {
        Empresa empresa = this.findById(empresaId);

        Set<Departamento> departamentos = departamentoRepository.findAllByEmpresaId(empresaId);
        if (!departamentos.isEmpty())
            throw new DataIntegrityViolationException(
                    COMPANY_WITH_ASSOCIATED_DEPARTMENT
                            .params(
                                    empresaId.toString()
                                    , departamentos.stream()
                                            .map(Departamento::getId)
                                            .map(UUID::toString)
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        empresaRepository.delete(empresa);
    }

}