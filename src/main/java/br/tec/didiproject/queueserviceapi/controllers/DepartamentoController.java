package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.DepartamentoControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.DepartamentoMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.services.DepartamentoService;
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
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_DEPARTAMENTO;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_DEPARTAMENTO)
@RequiredArgsConstructor
public class DepartamentoController implements DepartamentoControllerDocs {

    private final DepartamentoService departamentoService;
    private final DepartamentoMapper departamentoMapper;

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaDepartamentoDTO> novoDepartamento(
            @RequestBody @Valid RequisicaoDepartamentoDTO requisicaoDepartamentoDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        Departamento novoDepartamento = departamentoService.create(departamentoMapper.toEntity(requisicaoDepartamentoDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoDepartamento.getId().toString()).toUri();
        return ResponseEntity.created(uri).body(departamentoMapper.toResponseDTO(novoDepartamento));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaDepartamentoDTO>> listarDepartamentos(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Departamento> pageDepartamentos = departamentoService.findAll(pageable);

        List<RespostaDepartamentoDTO> respostaDTOs = departamentoMapper.toResponseDTOList(pageDepartamentos.getContent());
        Page<RespostaDepartamentoDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageDepartamentos.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaDepartamentoDTO> findById(@PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok()
                .body(departamentoMapper.toResponseDTO(departamentoService.findById(UUID.fromString(id))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_USUARIO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaDepartamentoDTO> atualizaDepartamento(
            @PathVariable String id
            , @RequestBody @Valid RequisicaoDepartamentoDTO requisicaoDepartamentoDTO
            , BindingResult bindingResult) {

        validateUUIDPattern(id);
        checkBindingResultError(bindingResult);

        return ResponseEntity.ok()
                .body(departamentoMapper.toResponseDTO(departamentoService.atualizarDepartamento(
                        UUID.fromString(id)
                        , departamentoMapper.toEntity(requisicaoDepartamentoDTO))));
    }

    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarDepartamento(@PathVariable String id) {
        validateUUIDPattern(id);
        departamentoService.deletarDepartamento(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
