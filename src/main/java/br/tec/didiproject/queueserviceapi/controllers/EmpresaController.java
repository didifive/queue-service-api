package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.EmpresaControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.EmpresaMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoEmpresaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaEmpresaDTO;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;
import br.tec.didiproject.queueserviceapi.services.EmpresaService;
import br.tec.didiproject.queueserviceapi.utils.valid_uuid.ValidUUID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController implements EmpresaControllerDocs {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    private static void checkBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBodyException(
                    bindingResult.getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining("||")));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaEmpresaDTO> novaEmpresa(
            @RequestBody @Valid RequisicaoEmpresaDTO requisicaoEmpresaDTO
            , BindingResult bindingResult) {

        checkBindingResult(bindingResult);

        Empresa novaEmpresa = empresaService.create(empresaMapper.toEntity(requisicaoEmpresaDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaEmpresa.getId().toString()).toUri();
        return ResponseEntity.created(uri).body(empresaMapper.toResponseDTO(novaEmpresa));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaEmpresaDTO>> listarEmpresas(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Empresa> pageEmpresas = empresaService.findAll(pageable);

        List<RespostaEmpresaDTO> respostaDTOs = empresaMapper.toResponseDTOList(pageEmpresas.getContent());
        Page<RespostaEmpresaDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageEmpresas.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaEmpresaDTO> findById(@PathVariable @ValidUUID String id) {
        return ResponseEntity.ok()
                .body(empresaMapper.toResponseDTO(empresaService.findById(UUID.fromString(id))));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaEmpresaDTO> atualizaEmpresa(
            @PathVariable @ValidUUID String id
            , @RequestBody @Valid RequisicaoEmpresaDTO requisicaoEmpresaDTO
            , BindingResult bindingResult) {

        checkBindingResult(bindingResult);

        return ResponseEntity.ok()
                .body(empresaMapper.toResponseDTO(empresaService.atualizarEmpresa(
                        UUID.fromString(id)
                        , empresaMapper.toEntity(requisicaoEmpresaDTO))));
    }

    @Override
    public ResponseEntity<Void> deletarEmpresa(@PathVariable @ValidUUID String id) {
        empresaService.deletarEmpresa(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }

}
