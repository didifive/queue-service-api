package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.enums.Perfil;
import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.AtendenteRepository;
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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final AtendenteRepository atendenteRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * CRUD: Read
     * Find user by username
     *
     * @param nomeUsuario String with a username
     */
    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        return usuarioRepository.findByNomeUsuarioIgnoreCase(nomeUsuario).orElseThrow(
                () -> new UsernameNotFoundException(USER_BY_USERNAME_NOT_FOUND
                        .params(nomeUsuario).getMessage()));
    }

    /**
     * CRUD: Read
     * Find user by id
     *
     * @param usuarioId UUID with the user id
     */
    public Usuario findById(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND
                        .params(usuarioId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find user by attendant
     *
     * @param atendenteId UUID with the attendant id
     */
    public Usuario findByAttendantId(UUID atendenteId) {
        return usuarioRepository.findByAtendenteId(atendenteId).orElseThrow(
                () -> new EntityNotFoundException(USER_BY_ATTENDANT_NOT_FOUND
                        .params(atendenteId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find users and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }


    /**
     * CRUD: Create
     * Create a new user with validation to prevent username repeat
     *
     * @param novoUsuario Usuario object with the new user data
     */
    public Usuario create(Usuario novoUsuario) {
        this.validarNomeUsuario(novoUsuario.getNomeUsuario());

        if (Objects.nonNull(novoUsuario.getAtendente().getId())) {
            atendenteRepository.findById(novoUsuario.getAtendente().getId());
        }

        novoUsuario.setSenha(this.validarCriptografarSenha(novoUsuario.getPassword()));

        novoUsuario.setAtivo(Boolean.TRUE);

        return usuarioRepository.save(novoUsuario);
    }

    private String validarCriptografarSenha(String senha) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%^&*+=])"
                + "(?=\\S+$).{6,60}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha);
        if (!m.matches())
            throw new BadRequestBodyException(USER_INVALID_PASSWORD.getMessage());

        return bCryptPasswordEncoder.encode(senha);
    }

    /**
     * CRUD: Update
     * Update username with validation of the new username to prevent username repeat
     *
     * @param usuarioId       UUID with the id of the existing user
     * @param novoNomeUsuario String with a new username to update a existing username user
     */
    public Usuario atualizarNomeUsuario(UUID usuarioId, String novoNomeUsuario) {
        Usuario usuarioExistente = this.findById(usuarioId);

        if (!novoNomeUsuario.equals(usuarioExistente.getNomeUsuario()))
            validarNomeUsuario(novoNomeUsuario);

        usuarioExistente.setNomeUsuario(novoNomeUsuario);

        return usuarioRepository.save(usuarioExistente);
    }

    /**
     * CRUD: Update
     * Update user password
     *
     * @param usuarioId  UUID with the id of the existing user
     * @param senhaAtual String with current password
     * @param novaSenha  String with a new password
     */
    public Usuario atualizarSenha(UUID usuarioId, String senhaAtual, String novaSenha) {

        Usuario usuarioExistente = this.findById(usuarioId);

        if (!bCryptPasswordEncoder.matches(senhaAtual, usuarioExistente.getSenha()))
            throw new DataIntegrityViolationException(USER_WRONG_PASSWORD
                    .params(usuarioExistente.getId().toString()).getMessage());

        usuarioExistente.setSenha(this.validarCriptografarSenha(novaSenha));

        return usuarioRepository.save(usuarioExistente);
    }

    /**
     * CRUD: Update
     * Add a Role to User
     *
     * @param usuarioId  UUID with the user Id
     * @param nomePerfil String with the new role name
     */
    public Usuario adicionarPerfil(UUID usuarioId, String nomePerfil) {
        Usuario usuario = this.findById(usuarioId);
        Set<Perfil> perfis = new HashSet<>(usuario.getPerfis());

        try {
            perfis.add(Perfil.valueOf(nomePerfil));
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(
                    ROLE_NOT_FOUND_BY_NAME.params(nomePerfil).getMessage());
        }

        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }

    /**
     * CRUD: Update
     * Remove a Role to User
     *
     * @param usuarioId  UUID with the user Id
     * @param nomePerfil String with the role name
     */
    public Usuario removerPerfil(UUID usuarioId, String nomePerfil) {
        Usuario usuario = this.findById(usuarioId);

        Perfil perfil;
        try {
            perfil = Perfil.valueOf(nomePerfil);
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(
                    ROLE_NOT_FOUND_BY_NAME.params(nomePerfil).getMessage());
        }

        Set<Perfil> perfis = new HashSet<>(usuario.getPerfis());
        perfis.remove(perfil);

        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }

    /**
     * CRUD: Update
     * Active User
     *
     * @param usuarioId UUID with the user Id
     */
    public Usuario ativarUsuario(UUID usuarioId) {
        Usuario usuario = this.findById(usuarioId);
        usuario.setAtivo(Boolean.TRUE);
        return usuarioRepository.save(usuario);
    }

    /**
     * CRUD: Update
     * Disable User
     *
     * @param usuarioId UUID with the user Id
     */
    public Usuario desativarUsuario(UUID usuarioId) {
        Usuario usuario = this.findById(usuarioId);
        usuario.setAtivo(Boolean.FALSE);
        return usuarioRepository.save(usuario);
    }

    /**
     * CRUD: Update
     * Detach Atendente
     *
     * @param usuarioId UUID with the user Id
     */
    public Usuario desligarAtendente(UUID usuarioId) {
        Usuario usuario = this.findById(usuarioId);
        usuario.setAtendente(null);
        return usuarioRepository.save(usuario);
    }

    /**
     * This method check if a new username already exists in the database
     * and throws a DataIntegrityViolationException if existing
     *
     * @param nomeUsuario String with a new username to check
     */
    private void validarNomeUsuario(String nomeUsuario) {
        if (usuarioRepository.findByNomeUsuarioIgnoreCase(nomeUsuario).isPresent())
            throw new DataIntegrityViolationException(
                    USER_ALREADY_EXISTS.params(nomeUsuario).getMessage());
    }

}