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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.SecurityAuthority.*;
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
    @Override
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

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/{id}/chamar-senha")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<RespostaSenhaDTO> chamarSenha(
            @PathVariable String id
            , @RequestParam(required = false) Boolean rechamada) {

        validateUUIDPattern(id);

        if (Objects.isNull(rechamada))
            rechamada = Boolean.FALSE;

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.chamarSenha(UUID.fromString(id), rechamada)
        ));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/fila/{filaId}/chamar-senha")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<RespostaSenhaDTO> chamarProximaSenha(
            @PathVariable String filaId) {

        validateUUIDPattern(filaId);

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.chamarProximaSenha(UUID.fromString(filaId))
        ));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/{id}/finalizar-senha")
    @ResponseStatus(HttpStatus.OK)
    @Override
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

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/finalizar-senhas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
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

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/finalizar-todas-senhas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public ResponseEntity<Void> finalizarTodasSenhasNaoFinalizadas(
            @RequestBody @Valid RequisicaoSenhaFinalizaSenhaDTO requisicaoSenhaFinalizaSenhaDTO
            , BindingResult bindingResult
            , Authentication authentication) {

        checkBindingResultError(bindingResult);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        senhaService.finalizarTodasSenhasNaoFinalizadas(
                requisicaoSenhaFinalizaSenhaDTO.getMotivoFinalizada()
                , usuario
        );

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @PatchMapping("/{id}/atender-senha")
    @ResponseStatus(HttpStatus.OK)
    @Override
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

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @PatchMapping("/{id}/resetar-status")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<RespostaSenhaDTO> resetarStatusSenha(
            @PathVariable String id) {

        validateUUIDPattern(id);

        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.resetarStatusSenha(UUID.fromString(id))
        ));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<RespostaSenhaDTO> findById(
            @PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok().body(senhaMapper.toResponseDTO(
                senhaService.findById(UUID.fromString(id))
        ));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhas(
            @PageableDefault(size = 100, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable
            , Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Page<Senha> pageSenhas = senhaService.findAll(pageable, usuario);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_ATENDENTE)
    @GetMapping("/nao-finalizadas")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhasNaoFinalizadas(
            @PageableDefault(size = 50, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable
            , Authentication authentication
    ) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Page<Senha> pageSenhas = senhaService.senhasNaoFinalizadas(pageable, usuario);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/{dataInicio}/{dataFim}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhasPorIntervaloDias(
            @PathVariable String dataInicio
            , @PathVariable String dataFim
            , @PageableDefault(size = 20, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Senha> pageSenhas = senhaService.senhasPorIntervaloDataDeGeracao(dataInicio, dataFim, pageable);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/chamadas/{dataInicio}/{dataFim}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhasChamadasPorIntervaloDias(
            @PathVariable String dataInicio
            , @PathVariable String dataFim
            , @PageableDefault(size = 20, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Senha> pageSenhas = senhaService.senhasChamadasPorIntervaloData(dataInicio, dataFim, pageable);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/finalizadas/{dataInicio}/{dataFim}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhasFinalizadasPorIntervaloDias(
            @PathVariable String dataInicio
            , @PathVariable String dataFim
            , @PageableDefault(size = 20, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Senha> pageSenhas = senhaService.senhasFinalizadasPorIntervaloData(dataInicio, dataFim, pageable);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }


    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/atendidas/{dataInicio}/{dataFim}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Page<RespostaSenhaDTO>> listarSenhasAtendidasPorIntervaloDias(
            @PathVariable String dataInicio
            , @PathVariable String dataFim
            , @PageableDefault(size = 20, sort = "geradaEm", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Senha> pageSenhas = senhaService.senhasAtendidasPorIntervaloData(dataInicio, dataFim, pageable);

        List<RespostaSenhaDTO> respostaDTOs = senhaMapper.toResponseDTOList(pageSenhas.getContent());
        Page<RespostaSenhaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageSenhas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }
}
