package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.UsuarioControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.UsuarioMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioAtualizarSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioNovoNomeUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioPerfilDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaUsuarioDTO;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.SecurityAuthority.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_USUARIO;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_USUARIO)
@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerDocs {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaUsuarioDTO> novoUsuario(
            @RequestBody @Valid RequisicaoUsuarioDTO requisicaoUsuarioDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        Usuario novoUsuario = usuarioService.create(usuarioMapper.toEntity(requisicaoUsuarioDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoUsuario.getId().toString()).toUri();

        return ResponseEntity.created(uri).body(usuarioMapper.toResponseDTO(novoUsuario));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaUsuarioDTO>> listarUsuarios(
            @PageableDefault(sort = "nomeUsuario", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Usuario> pageUsuarios = usuarioService.findAll(pageable);

        List<RespostaUsuarioDTO> respostaDTOs = usuarioMapper.toResponseDTOList(pageUsuarios.getContent());
        Page<RespostaUsuarioDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageUsuarios.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_OWN_USER)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> findById(@PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_OWN_USER)
    @PatchMapping("/{id}/novo-nome-usuario")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> novoNomeUsuario(
            @PathVariable String id
            , @RequestBody RequisicaoUsuarioNovoNomeUsuarioDTO requisicaoUsuarioNovoNomeUsuarioDTO) {

        validateUUIDPattern(id);

        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.atualizarNomeUsuario(
                        UUID.fromString(id)
                        , requisicaoUsuarioNovoNomeUsuarioDTO.getNomeUsuario())));
    }

    @PreAuthorize(OWN_USER)
    @PatchMapping("/{id}/atualizar-senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<RespostaUsuarioDTO> atualizarSenha(
            @PathVariable String id
            , @RequestBody RequisicaoUsuarioAtualizarSenhaDTO requisicaoUsuarioAtualizarSenhaDTO) {

        validateUUIDPattern(id);

        usuarioService.atualizarSenha(UUID.fromString(id)
                , requisicaoUsuarioAtualizarSenhaDTO.getSenhaAtual()
                , requisicaoUsuarioAtualizarSenhaDTO.getNovaSenha());

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_AND_NOT_OWN_USER)
    @PatchMapping("/{id}/perfil/adicionar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> adicionarPerfil(
            @PathVariable String id
            , @RequestBody RequisicaoUsuarioPerfilDTO requisicaoUsuarioPerfilDTO) {

        validateUUIDPattern(id);

        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.adicionarPerfil(
                        UUID.fromString(id)
                        , requisicaoUsuarioPerfilDTO.getPerfil().name())));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_AND_NOT_OWN_USER)
    @PatchMapping("/{id}/perfil/remover")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> removerPerfil(
            @PathVariable String id
            , @RequestBody RequisicaoUsuarioPerfilDTO requisicaoUsuarioPerfilDTO) {

        validateUUIDPattern(id);

        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.removerPerfil(
                        UUID.fromString(id)
                        , requisicaoUsuarioPerfilDTO.getPerfil().name())));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_AND_NOT_OWN_USER)
    @PatchMapping("/{id}/ativar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> ativarUsuario(
            @PathVariable String id) {

        validateUUIDPattern(id);

        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.ativarUsuario(
                        UUID.fromString(id))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_AND_NOT_OWN_USER)
    @PatchMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaUsuarioDTO> desativarUsuario(
            @PathVariable String id) {

        validateUUIDPattern(id);

        return ResponseEntity.ok()
                .body(usuarioMapper.toResponseDTO(usuarioService.desativarUsuario(
                        UUID.fromString(id))));
    }
}
