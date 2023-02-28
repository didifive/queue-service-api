package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.AtendenteRepository;
import br.tec.didiproject.queueserviceapi.repositories.SenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.ATTENDANT_NOT_FOUND;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.ATTENDANT_WITH_ASSOCIATED_SERVICE;

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

        novoAtendente = atendenteRepository.save(novoAtendente);

        Usuario novoUsuario = Usuario
                .builder()
                .nomeUsuario(novoAtendente.getEmail())
                .senha(novoAtendente.getEmail())
                .atendente(novoAtendente)
                .perfis(new HashSet<>())
                .build();
        usuarioService.create(novoUsuario);

        return novoAtendente;
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

        atendenteRepository.delete(atendente);
    }

}