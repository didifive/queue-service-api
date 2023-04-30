package br.tec.didiproject.queueserviceapi.utils.db.populate;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.repositories.UsuarioRepository;
import br.tec.didiproject.queueserviceapi.services.AtendenteService;
import br.tec.didiproject.queueserviceapi.services.DepartamentoService;
import br.tec.didiproject.queueserviceapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PopularAtendente {

    private final AtendenteService atendenteService;
    private final DepartamentoService departamentoService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final Pageable pageable = PageRequest.of(0, 10);

    private final Atendente novoAtendente = Atendente.builder()
            .nome("João da Silva")
            .email("joao@email.com")
            .departamentos(new HashSet<>())
            .build();

    public void popularAtendente() {

        Page<Atendente> atendentes = atendenteService.findAll(pageable);

        if (atendentes.getContent().isEmpty()) {

            novoAtendente.setDepartamentos(new HashSet<>(departamentoService.findAll(pageable).getContent()));

            this.limpaUsuarios();

            atendenteService.create(novoAtendente);

            Usuario usuario = usuarioService.findAll(pageable).getContent().get(0);
            usuarioService.adicionarPerfil(usuario.getId(), "ADMIN");
            usuarioService.adicionarPerfil(usuario.getId(), "USUARIO");
            usuarioService.adicionarPerfil(usuario.getId(), "ATENDENTE");
        }

    }

    private void limpaUsuarios() {
        List<Usuario> usuariosExistentes = usuarioService.findAll(pageable).getContent();
        usuarioRepository.deleteAll(usuariosExistentes);
        List<Usuario> aindaExisteUsuarios = usuarioService.findAll(pageable).getContent();
        if (!aindaExisteUsuarios.isEmpty()) this.limpaUsuarios();
    }
}
