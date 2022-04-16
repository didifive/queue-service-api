package br.tec.fivedti.queueserviceapi.repositories;

import br.tec.fivedti.queueserviceapi.entities.QueueRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QueueRepository extends JpaRepository<QueueRow, UUID> {

    QueueRow findByAbbreviation(String abbreviation);

}