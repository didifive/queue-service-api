package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Perfil;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * CRUD: Read
     * Find user by username
     * @param nomeUsuario String with a username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        return usuarioRepository.findByNomeUsuarioIgnoreCase(nomeUsuario).orElseThrow(
                ()-> new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME
                        .params(nomeUsuario).getMessage()));
    }

    /**
     * CRUD: Read
     * Find user by id
     * @param usuarioId UUID with the user id
     * @return Usuario
     */
    public Usuario findById(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND
                        .params(usuarioId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find users and list with pageable content
     * @param pageable Pageable object with page options
     * @return Page<Usuario>
     */
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }



    /**
     * CRUD: Create
     * Create a new user with validation to prevent username repeat
     * @param novoUsuario Usuario object with the new user data
     * @return Usuario
     */
    public Usuario create(Usuario novoUsuario) {
        this.validarNomeUsuario(novoUsuario.getNomeUsuario());

        novoUsuario.setSenha(bCryptPasswordEncoder.encode(novoUsuario.getSenha()));

        return usuarioRepository.save(novoUsuario);
    }

    /**
     * CRUD: Update
     * Update username with validation of the new username to prevent username repeat
     * @param usuarioId UUID with the id of the existing user
     * @param novoNomeUsuario String with a new username to update a existing username user
     * @return Usuario
     */
    public Usuario atualizarNomeUsuario(UUID usuarioId, String novoNomeUsuario) {
        Usuario usuarioExistente = this.findById(usuarioId);

        if(!novoNomeUsuario.equals(usuarioExistente.getNomeUsuario()))
            validarNomeUsuario(novoNomeUsuario);

        usuarioExistente.setNomeUsuario(novoNomeUsuario);

        return  usuarioRepository.save(usuarioExistente);
    }

    /**
     * CRUD: Update
     * Update user password
     * @param usuarioId UUID with the id of the existing user
     * @param senhaAtual String with current password
     * @param novaSenha String with a new password
     * @return Usuario
     */
    public Usuario atualizarSenha(UUID usuarioId, String senhaAtual, String novaSenha) {

        Usuario usuarioExistente = this.findById(usuarioId);

        if(!bCryptPasswordEncoder.matches(senhaAtual, usuarioExistente.getSenha()))
            throw new DataIntegrityViolationException(USER_WRONG_PASSWORD
                    .params(usuarioExistente.getId().toString()).getMessage());

        usuarioExistente.setSenha(bCryptPasswordEncoder.encode(novaSenha));

        return usuarioRepository.save(usuarioExistente);
    }

    /**
     * CRUD: Update
     * Add a Role to User
     * @param usuarioId UUID with the user Id
     * @param novoPerfilId UUID with the new role Id
     * @return Usuario
     */
    public Usuario adicionarPerfil(UUID usuarioId, UUID novoPerfilId) {
        Usuario usuario = this.findById(usuarioId);
        Perfil novoPerfil = perfilService.findById(novoPerfilId);

        Set<Perfil> perfis = new HashSet<>(usuario.getPerfis());
        perfis.add(novoPerfil);

        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }

    /**
     * CRUD: Update
     * Remove a Role to User
     * @param usuarioId UUID with the user Id
     * @param perfilId UUID with the role Id to be removed
     * @return Usuario
     */
    public Usuario removerPerfil(UUID usuarioId, UUID perfilId) {
        Usuario usuario = this.findById(usuarioId);
        Perfil perfil = perfilService.findById(perfilId);

        Set<Perfil> perfis = new HashSet<>(usuario.getPerfis());
        perfis.remove(perfil);

        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }

    /**
     * This method check if a new username already exists in the database
     * and throws a DataIntegrityViolationException if existing
     * @param nomeUsuario String with a new username to check
     * @return void
     */
    private void validarNomeUsuario(String nomeUsuario) {
        if (usuarioRepository.findByNomeUsuarioIgnoreCase(nomeUsuario).isPresent())
            throw new DataIntegrityViolationException(
                    USER_ALREADY_EXISTS.params(nomeUsuario).getMessage());
    }

}