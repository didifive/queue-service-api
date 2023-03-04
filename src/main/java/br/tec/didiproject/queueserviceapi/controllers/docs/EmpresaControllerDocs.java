package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.ApiErrorDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoEmpresaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaEmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@SecurityRequirement(name = SECURITY_SCHEME_NAME)
@Tag(name = EMPRESA_CONTROLER_TAG)
public interface EmpresaControllerDocs {

    @Tag(name=TAG_POST)
    @Operation(summary = EMPRESA_CONTROLLER_CREATE_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "201"
            , description = EMPRESA_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            , description = EMPRESA_CONTROLLER_CREATE_400_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_CREATE_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<RespostaEmpresaDTO> novaEmpresa(RequisicaoEmpresaDTO requisicaoEmpresaDTO);

    @Tag(name=TAG_GET)
    @Operation(summary = EMPRESA_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            , name = "size"
            , description = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
            , example = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            , name = "page"
            , description = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            , example = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "sort"
            , description = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            , example = EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = EMPRESA_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_CREATE_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<RespostaEmpresaDTO>> listarEmpresas(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    );


//    CompanyDto findById(UUID id) throws CompanyNotFoundException;
//
//
//    MessageResponseDto update(UUID id, CompanyDto companyDto) throws CompanyNotFoundException;
//
//
//    MessageResponseDto delete(UUID id) throws CompanyNotFoundException;
}