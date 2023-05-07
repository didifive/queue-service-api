package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("NullableProblems")
@Repository
public interface SenhaRepository extends JpaRepository<Senha, UUID>, JpaSpecificationExecutor<Senha> {

    Optional<Senha> findFirstByFilaIdAndTipoAtendimentoIdAndChamadaEmIsNullAndFinalizadaEmIsNull(
            UUID filaId
            , UUID tipoAtendimentoId
    );

    Optional<Senha> findFirstByFilaIdOrderByGeradaEmDesc(
            UUID filaId
    );

    Page<Senha> findAll(
            Pageable pageable
    );

    List<Senha> findAllByFilaDepartamento(
            Departamento departamento
    );

    Page<Senha> findAllByFinalizadaEmIsNull(
            Pageable pageable
    );

    List<Senha> findAllByFinalizadaEmIsNullAndFilaDepartamento(
            Departamento departamento
    );

    Page<Senha> findAllByAtendenteId(
            UUID atendenteId
            , Pageable pageable
    );

    Page<Senha> findAllByTipoAtendimentoId(
            UUID tipoAtendimentoId
            , Pageable pageable
    );

    Page<Senha> findAllByFilaId(
            UUID filaId
            , Pageable pageable
    );

    Page<Senha> findAllByFilaIdAndTipoAtendimentoIdAndFinalizadaEmIsNullAndMotivoFinalizadaIsNull(
            UUID filaId
            , UUID tipoAtendimentoId
            , Pageable pageable
    );

    Page<Senha> findAllByGeradaEmBetween(
            Date dateTimeStart
            , Date dateTimeEnd
            , Pageable pageable
    );

    Page<Senha> findAllByChamadaEmBetween(
            Date dateTimeStart
            , Date dateTimeEnd
            , Pageable pageable
    );

    Page<Senha> findAllByFinalizadaEmBetween(
            Date dateTimeStart
            , Date dateTimeEnd
            , Pageable pageable
    );

    Page<Senha> findAllByAtendidaEmBetween(
            Date dateTimeStart
            , Date dateTimeEnd
            , Pageable pageable
    );
}