package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilaRepository extends JpaRepository<Fila, UUID> {

    Page<Fila> findAllByTiposAtendimentoId(UUID tipoAtendimentoId, Pageable pageable);

    Page<Fila> findAllByDepartamentoId(UUID departamentoId, Pageable pageRequest);

    Page<Fila> findAll(Pageable pageable);
}