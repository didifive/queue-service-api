package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.exceptions.QueueServiceApiException;
import br.tec.didiproject.queueserviceapi.repositories.AtendenteRepository;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@RequiredArgsConstructor
@Service
public class AtendenteService {

    private final AtendenteRepository atendenteRepository;
    private final SenhaRepository senhaRepository;
    private final UsuarioService usuarioService;

    /**
     * CRUD: Read
     * Find attendant by id
     *
     * @param atendenteId UUID with the department id
     */
    public Atendente findById(UUID atendenteId) {
        return atendenteRepository.findById(atendenteId).orElseThrow(
                () -> new EntityNotFoundException(
                        ATTENDANT_NOT_FOUND.params(atendenteId.toString()).getMessage()));
    }

    /**
     * CRUD: Read
     * Find attendants and list with pageable content
     *
     * @param pageable Pageable object with page options
     */
    public Page<Atendente> findAll(Pageable pageable) {
        return atendenteRepository.findAll(pageable);
    }

    /**
     * CRUD: Create
     * Create a new attendant
     *
     * @param novoAtendente Atendente object with the new company data
     */
    public Atendente create(Atendente novoAtendente) {

        try {
            novoAtendente = atendenteRepository.save(novoAtendente);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            Pattern pattern = Pattern.compile(novoAtendente.getEmail());
            Matcher matcher = pattern.matcher(e.getCause().getCause().getMessage());
            if (matcher.find())
                throw new DataIntegrityViolationException(
                        ATTENDANT_WITH_DUPLICATE_EMAIL
                                .params(novoAtendente.getEmail())
                                .getMessage());
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        } catch (Exception e) {
            throw new QueueServiceApiException(GENERIC_EXCEPTION.getMessage());
        }

        Usuario novoUsuario = Usuario
                .builder()
                .nomeUsuario(novoAtendente.getEmail())
                .senha("Pw5@QueueService")
                .atendente(novoAtendente)
                .perfis(new HashSet<>())
                .build();
        this.criarUsuario(novoUsuario);

        return novoAtendente;
    }

    private void criarUsuario(Usuario novoUsuario) {
        try {
            usuarioService.create(novoUsuario);
        } catch (DataIntegrityViolationException e) {
            String[] nomeUsuarioSplit = novoUsuario.getNomeUsuario().split("@");
            String ultimoCaractereNomeUsuario = nomeUsuarioSplit[0].substring(nomeUsuarioSplit[0].length() - 1);
            if (NumberUtils.isDigits(ultimoCaractereNomeUsuario)
                    && Integer.parseInt(ultimoCaractereNomeUsuario) != 9)
                nomeUsuarioSplit[0] = nomeUsuarioSplit[0].substring(0,nomeUsuarioSplit[0].length() - 1)
                                    + (Integer.parseInt(ultimoCaractereNomeUsuario) + 1);
            else
                nomeUsuarioSplit[0] = nomeUsuarioSplit[0] + "1";
            String novoNomeUsuario = String.join("@", nomeUsuarioSplit);
            novoUsuario.setNomeUsuario(novoNomeUsuario);
            novoUsuario.setSenha(novoNomeUsuario);
            this.criarUsuario(novoUsuario);
        }
    }

    /**
     * CRUD: Update
     * Update attendant data
     *
     * @param atendenteId   UUID with the id of the existing attendant
     * @param novoAtendente Atendente object with a new attendant data
     */
    public Atendente atualizarAtendente(UUID atendenteId, Atendente novoAtendente) {
        Atendente atendenteExistente = this.findById(atendenteId);

        novoAtendente.setId(atendenteExistente.getId());

        Usuario usuarioVinculado = usuarioService.findByAttendantId(atendenteId);
        usuarioService.atualizarNomeUsuario(usuarioVinculado.getId(), novoAtendente.getEmail());

        return atendenteRepository.save(novoAtendente);
    }

    /**
     * CRUD: Delete
     * Delete the attendant
     *
     * @param atendenteId UUID with the id of the existing attendant
     */
    public void deletarAtendente(UUID atendenteId) {
        Atendente atendente = this.findById(atendenteId);

        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Senha> senhas = senhaRepository.findAllByAtendenteId(atendenteId, pageRequest);
        if (senhas.getTotalElements() > 0)
            throw new DataIntegrityViolationException(
                    ATTENDANT_WITH_ASSOCIATED_SERVICE
                            .params(
                                    atendenteId.toString()
                                    , senhas.stream()
                                            .map(s -> s.getId().toString())
                                            .collect(Collectors.joining(", ")))
                            .getMessage());

        Usuario usuarioVinculado = usuarioService.findByAttendantId(atendente.getId());
        usuarioService.desligarAtendente(usuarioVinculado.getId());
        usuarioService.desativarUsuario(usuarioVinculado.getId());

        atendenteRepository.delete(atendente);
    }

}