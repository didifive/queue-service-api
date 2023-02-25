package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface AtendenteRepository extends JpaRepository<Atendente, UUID> {

    Set<Atendente> findByDepartamentosContains(Departamento departamento);

}