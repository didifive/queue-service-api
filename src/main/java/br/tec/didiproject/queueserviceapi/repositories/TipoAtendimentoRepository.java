package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, UUID>, JpaSpecificationExecutor<Senha> {

    Page<TipoAtendimento> findAll(Pageable pageable);

}