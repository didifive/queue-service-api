package br.tec.didiproject.queueserviceapi.repositories;

import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Senha> {

    Optional<Usuario> findByNomeUsuarioIgnoreCase(String nomeUsuario);

    Optional<Usuario> findByAtendenteId(UUID atendenteId);

    @SuppressWarnings("NullableProblems")
    Page<Usuario> findAll(Pageable pageable);

}