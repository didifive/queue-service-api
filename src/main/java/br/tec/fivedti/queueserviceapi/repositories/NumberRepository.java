package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.NumberSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface NumberRepository extends JpaRepository<NumberSequence, UUID>, JpaSpecificationExecutor<NumberSequence> {
}