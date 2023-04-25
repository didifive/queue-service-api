package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.AtendenteControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.mapper.AtendenteMapper;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoAtendenteDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaAtendenteDTO;
import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.services.AtendenteService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_ATENDENTE;
import static br.tec.didiproject.queueserviceapi.utils.BindingError.checkBindingResultError;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RestController
@RequestMapping(PATH_ATENDENTE)
@RequiredArgsConstructor
public class AtendenteController implements AtendenteControllerDocs {

    private final AtendenteService atendenteService;
    private final UsuarioService usuarioService;
    private final AtendenteMapper atendenteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RespostaAtendenteDTO> novoAtendente(
            @RequestBody @Valid RequisicaoAtendenteDTO requisicaoAtendenteDTO
            , BindingResult bindingResult) {

        checkBindingResultError(bindingResult);

        Atendente novoAtendente = atendenteService.create(atendenteMapper.toEntity(requisicaoAtendenteDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoAtendente.getId().toString()).toUri();

        Usuario usuario = usuarioService.findByAttendantId(novoAtendente.getId());
        return ResponseEntity.created(uri).body(atendenteMapper.toResponseDTO(novoAtendente, usuario));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<RespostaAtendenteDTO>> listarAtendentes(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Atendente> pageAtendentes = atendenteService.findAll(pageable);

        List<RespostaAtendenteDTO> respostaDTOs = atendenteMapper.toResponseDTOList(pageAtendentes.getContent());
        Page<RespostaAtendenteDTO> pageRespostaDTOs = new PageImpl<>(respostaDTOs, pageable, pageAtendentes.getTotalElements());

        return ResponseEntity.ok().body(pageRespostaDTOs);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaAtendenteDTO> findById(@PathVariable String id) {
        validateUUIDPattern(id);
        return ResponseEntity.ok()
                .body(atendenteMapper.toResponseDTO(atendenteService.findById(UUID.fromString(id))
                        , usuarioService.findByAttendantId(UUID.fromString(id))));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaAtendenteDTO> atualizaAtendente(
            @PathVariable String id
            , @RequestBody @Valid RequisicaoAtendenteDTO requisicaoAtendenteDTO
            , BindingResult bindingResult) {

        validateUUIDPattern(id);
        checkBindingResultError(bindingResult);

        return ResponseEntity.ok()
                .body(atendenteMapper.toResponseDTO(atendenteService.atualizarAtendente(
                                UUID.fromString(id)
                                , atendenteMapper.toEntity(requisicaoAtendenteDTO))
                        , usuarioService.findByAttendantId(UUID.fromString(id))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarAtendente(@PathVariable String id) {
        validateUUIDPattern(id);
        atendenteService.deletarAtendente(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
