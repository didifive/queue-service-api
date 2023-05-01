package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, UUID> {

    Page<Atendente> findAllByDepartamentosContains(Departamento departamento, Pageable pageable);

    Page<Atendente> findAll(Pageable pageable);

}