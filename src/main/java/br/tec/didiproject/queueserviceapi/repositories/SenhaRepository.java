package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Senha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, UUID>, JpaSpecificationExecutor<Senha> {

    Page<Senha> findAllByAtendenteId(UUID atendenteId, Pageable pageable);

    Page<Senha> findAllByTipoAtendimentoId(UUID tipoAtendimentoId, Pageable pageable);

    Page<Senha> findAllByFilaId(UUID filaId, Pageable pageable);

    Page<Senha> findAllByFinalizadaEmIsNull(Pageable pageable);

    Page<Senha> findAllByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullOrderByGeradaEmAsc(
            UUID filaId
            , UUID tipoAtendimentoId
            , Pageable pageable
    );

    Page<Senha> findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndChamadaEmIsNotNullOrderByGeradaEmDesc(
            UUID filaId
            , UUID tipoAtendimentoId
            , Pageable pageable
    );

    Page<Senha> findAll(Pageable pageable);

    Optional<Senha> findFirstByFilaIdAndTipoAtendimentoIdOrderByNumeroDesc(
            UUID filaId, UUID tipoAtendimentoId
    );

}