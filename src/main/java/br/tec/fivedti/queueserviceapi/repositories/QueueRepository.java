package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
}