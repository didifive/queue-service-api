package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.SenhaControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.SenhaMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaFinalizaSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaNovaSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaSenhaDTO;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.services.SenhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_SENHA;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_SENHA)
@RequiredArgsConstructor
public class SenhaController implements SenhaControllerDocs {

    private final SenhaService senhaService;
    private final SenhaMapper senhaMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaSenhaDTO> novaSenha(
            @RequestBody @Valid RequisicaoSenhaNovaSenhaDTO novaSenhaDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        Senha novaSenha = senhaService.create(UUID.fromString(novaSenhaDTO.getFilaId())
                , UUID.fromString(novaSenhaDTO.getTipoAtendimentoId())
                , novaSenhaDTO.getReset());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaSenha.getId().toString()).toUri();

        return ResponseEntity.created(uri).body(senhaMapper.toResponseDTO(novaSenha));
    }

    @PatchMapping("/{id}/chamar-senha")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaSenhaDTO> chamarSenha(
            @PathVariable String id
            , @RequestParam(required = false) Boolean rechamada) {

        validateUUIDPattern(id);

        if (Objects.isNull(rechamada))
            rechamada = Boolean.FALSE;

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.chamaSenha(UUID.fromString(id),rechamada)
        ));
    }

    @PatchMapping("/fila/{filaId}/chamar-senha")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaSenhaDTO> chamarProximaSenha(
            @PathVariable String filaId) {

        validateUUIDPattern(filaId);

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.chamarProximaSenha(UUID.fromString(filaId))
        ));
    }

    @PatchMapping("/{id}/finalizar-senha")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaSenhaDTO> finalizarSenha(
            @PathVariable String id
            , @RequestBody @Valid RequisicaoSenhaFinalizaSenhaDTO requisicaoSenhaFinalizaSenhaDTO
            , BindingResult bindingResult) {

        validateUUIDPattern(id);

        checkBindingResultError(bindingResult);

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.finalizarSenha(UUID.fromString(id)
                        , requisicaoSenhaFinalizaSenhaDTO.getMotivoFinalizada())
        ));
    }

    @PatchMapping("/finalizar-senhas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> finalizarSenhaPorFilaETipoAtendimento(
            @RequestBody @Valid RequisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO requisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        senhaService.finalizarSenhaPorFilaETipoAtendimento(
                UUID.fromString(requisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO.getFilaId())
                , UUID.fromString(requisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO.getTipoAtendimentoId())
                , requisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO.getMotivoFinalizada()
        );

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/atender-senha")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaSenhaDTO> atenderSenha(
            @PathVariable String id
            , Authentication authentication) {

        validateUUIDPattern(id);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.atenderSenha(UUID.fromString(id)
                        , usuario)
        ));
    }

    @PatchMapping("/{id}/resetar-status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaSenhaDTO> resetarStatusSenha(
            @PathVariable String id) {

        validateUUIDPattern(id);

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.resetarStatusSenha(UUID.fromString(id))
        ));
    }
}
