package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilaRepository extends JpaRepository<Fila, UUID> {
    Fila findBySigla(String sigla);

    Page<Fila> findAllByTiposAtendimentoIdContains(UUID tipoAtendimentoId, Pageable pageable);

    Page<Fila> findAll(Pageable pageable);

}