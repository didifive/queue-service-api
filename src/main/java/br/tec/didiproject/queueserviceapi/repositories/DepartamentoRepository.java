package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

    List<Departamento> findByEmpresa(Empresa empresa);

}