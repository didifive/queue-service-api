package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.entities.Perfil;
import br.tec.didiproject.queueserviceapi.exceptions.DataIntegrityViolationException;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.repositories.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.ROLE_ALREADY_EXISTS;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.ROLE_NOT_FOUND;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.ROLE_NOT_FOUND_BY_NAME;

@RequiredArgsConstructor
@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public Perfil findByName(String nome) {
        return perfilRepository.findByNomeIgnoreCase(nome).orElseThrow(
                () -> new EntityNotFoundException(
                        ROLE_NOT_FOUND_BY_NAME.params(nome).getMessage()));
    }

    public Perfil create(String nome) {
        if (perfilRepository.findByNomeIgnoreCase(nome).isPresent())
            throw new DataIntegrityViolationException(
                    ROLE_ALREADY_EXISTS.params(nome).getMessage());

        Perfil novoPerfil = new Perfil(null, nome);

        return perfilRepository.save(novoPerfil);
    }

    public Perfil findById(UUID perfilId) {
        return perfilRepository.findById(perfilId).orElseThrow(
                () -> new EntityNotFoundException(
                        ROLE_NOT_FOUND.params(perfilId.toString()).getMessage()));
    }
}
