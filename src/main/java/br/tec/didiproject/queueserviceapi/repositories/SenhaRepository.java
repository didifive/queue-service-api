package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Senha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SenhaRepository extends JpaRepository<Senha, UUID>, JpaSpecificationExecutor<Senha> {

    Page<Senha> findAllByAtendenteId(UUID atendenteId, Pageable pageable);

    Page<Senha> findAllByTipoAtendimentoId(UUID tipoAtendimentoId, Pageable pageable);

    Page<Senha> findAllByAtendidaEmIsNull(Pageable pageable);

    Page<Senha> findAllByFinalizadaEmIsNull(Pageable pageable);

    Page<Senha> findAll(Pageable pageable);

}