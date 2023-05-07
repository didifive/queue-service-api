package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.TipoAtendimentoControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.TipoAtendimentoMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoTipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaTipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import br.tec.didiproject.queueserviceapi.services.TipoAtendimentoService;
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
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_TIPO_ATENDIMENTO;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_TIPO_ATENDIMENTO)
@RequiredArgsConstructor
public class TipoAtendimentoController implements TipoAtendimentoControllerDocs {

    private final TipoAtendimentoService tipoAtendimentoService;
    private final TipoAtendimentoMapper tipoAtendimentoMapper;

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaTipoAtendimentoDTO> novoTipoAtendimento(
            @RequestBody @Valid RequisicaoTipoAtendimentoDTO requisicaoTipoAtendimentoDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        TipoAtendimento novaTipoAtendimento = tipoAtendimentoService.create(tipoAtendimentoMapper.toEntity(requisicaoTipoAtendimentoDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaTipoAtendimento.getId().toString()).toUri();
        return ResponseEntity.created(uri).body(tipoAtendimentoMapper.toResponseDTO(novaTipoAtendimento));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaTipoAtendimentoDTO>> listarTiposAtendimento(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TipoAtendimento> pageTipoAtendimentos = tipoAtendimentoService.findAll(pageable);

        List<RespostaTipoAtendimentoDTO> respostaDTOs = tipoAtendimentoMapper.toResponseDTOList(pageTipoAtendimentos.getContent());
        Page<RespostaTipoAtendimentoDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageTipoAtendimentos.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaTipoAtendimentoDTO> findById(@PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok()
                .body(tipoAtendimentoMapper.toResponseDTO(tipoAtendimentoService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaTipoAtendimentoDTO> atualizaTipoAtendimento(
            @PathVariable String id
            , @RequestBody @Valid RequisicaoTipoAtendimentoDTO requisicaoTipoAtendimentoDTO
            , BindingResult bindingResult) {

        validateUUIDPattern(id);
        checkBindingResultError(bindingResult);

        return ResponseEntity.ok()
                .body(tipoAtendimentoMapper.toResponseDTO(tipoAtendimentoService.atualizarTipoAtendimento(
                        UUID.fromString(id)
                        , tipoAtendimentoMapper.toEntity(requisicaoTipoAtendimentoDTO))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarTipoAtendimento(@PathVariable String id) {
        validateUUIDPattern(id);
        tipoAtendimentoService.deletarTipoAtendimento(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
