package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.FilaControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.FilaMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoFilaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaFilaDTO;
import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.services.FilaService;
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

import static br.tec.didiproject.queueserviceapi.enums.constants.SecurityAuthority.HAS_AUTHORITY_ADMIN;
import static br.tec.didiproject.queueserviceapi.enums.constants.SecurityAuthority.HAS_AUTHORITY_ADMIN_OR_USUARIO;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_FILA;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_FILA)
@RequiredArgsConstructor
public class FilaController implements FilaControllerDocs {

    private final FilaService filaService;
    private final FilaMapper filaMapper;

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaFilaDTO> novaFila(
            @RequestBody @Valid RequisicaoFilaDTO requisicaoFilaDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        Fila novoFila = filaService.create(filaMapper.toEntity(requisicaoFilaDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoFila.getId().toString()).toUri();

        return ResponseEntity.created(uri).body(filaMapper.toResponseDTO(novoFila));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaFilaDTO>> listarFilas(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Fila> pageFilas = filaService.findAll(pageable);

        List<RespostaFilaDTO> respostaDTOs = filaMapper.toResponseDTOList(pageFilas.getContent());
        Page<RespostaFilaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageFilas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/departamento/{departamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaFilaDTO>> listarFilasPorDepartamento(
            @PathVariable String departamentoId,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        validateUUIDPattern(departamentoId);

        Page<Fila> pageFilas = filaService.findAllByDepartamentoId(UUID.fromString(departamentoId), pageable);

        List<RespostaFilaDTO> respostaDTOs = filaMapper.toResponseDTOList(pageFilas.getContent());
        Page<RespostaFilaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageFilas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaFilaDTO> findById(@PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok()
                .body(filaMapper.toResponseDTO(filaService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaFilaDTO> atualizaFila(
            @PathVariable String id
            , @RequestBody @Valid RequisicaoFilaDTO requisicaoFilaDTO
            , BindingResult bindingResult) {

        validateUUIDPattern(id);
        checkBindingResultError(bindingResult);

        return ResponseEntity.ok()
                .body(filaMapper.toResponseDTO(filaService.atualizarFila(
                        UUID.fromString(id)
                        , filaMapper.toEntity(requisicaoFilaDTO))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PatchMapping("/{id}/tipo-atendimento/{tipoAtendimentoId}/adicionar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaFilaDTO> adicionarTipoAtendimento(
            @PathVariable String id
            , @PathVariable String tipoAtendimentoId) {

        validateUUIDPattern(id);
        validateUUIDPattern(tipoAtendimentoId);

        return ResponseEntity.ok()
                .body(filaMapper.toResponseDTO(filaService.adicionarTipoAtendimento(
                        UUID.fromString(id)
                        , UUID.fromString(tipoAtendimentoId))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PatchMapping("/{id}/tipo-atendimento/{tipoAtendimentoId}/remover")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaFilaDTO> removerTipoAtendimento(
            @PathVariable String id
            , @PathVariable String tipoAtendimentoId) {

        validateUUIDPattern(id);
        validateUUIDPattern(tipoAtendimentoId);

        return ResponseEntity.ok()
                .body(filaMapper.toResponseDTO(filaService.removerTipoAtendimento(
                        UUID.fromString(id)
                        , UUID.fromString(tipoAtendimentoId))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarFila(@PathVariable String id) {
        validateUUIDPattern(id);
        filaService.deletarFila(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
