package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface FilaRepository extends JpaRepository<Fila, UUID> {
    Fila findBySigla(String sigla);

    Set<Fila> findByTiposAtendimentoContains(TipoAtendimento tipoAtendimento);

}