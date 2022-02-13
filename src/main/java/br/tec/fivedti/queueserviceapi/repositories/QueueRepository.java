package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QueueRepository extends JpaRepository<Queue, UUID> {

    Queue findByAbbreviation(String abbreviation);

}