package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.ApiErrorDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaDepartamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@SecurityRequirement(name = SECURITY_SCHEME_NAME)
@Tag(name = DEPARTAMENTO_CONTROLER_TAG)
public interface DepartamentoControllerDocs {

    @Tag(name=TAG_POST)
    @Operation(summary = DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_SUMMARY
            , description = DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "201"
            , description = DEPARTAMENTO_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            , description = DEPARTAMENTO_CONTROLLER_400_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
            , description = DEPARTAMENTO_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<RespostaDepartamentoDTO> novoDepartamento(
            RequisicaoDepartamentoDTO requisicaoDepartamentoDTO
            , BindingResult bindingResult);

    @Tag(name=TAG_GET)
    @Operation(summary = DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
            , description = DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            , name = "size"
            , description = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
            , example = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            , name = "page"
            , description = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            , example = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "sort"
            , description = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            , example = DEPARTAMENTO_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = DEPARTAMENTO_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = DEPARTAMENTO_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<RespostaDepartamentoDTO>> listarDepartamentos(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    );

    @Tag(name=TAG_GET)
    @Operation(summary = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
            , description = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = DEPARTAMENTO_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = DEPARTAMENTO_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<RespostaDepartamentoDTO> findById(String id);

    @Tag(name=TAG_PUT)
    @Operation(summary = DEPARTAMENTO_CONTROLLER_UPDATE_OPERATION_SUMMARY
            , description = DEPARTAMENTO_CONTROLLER_UPDATE_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = DEPARTAMENTO_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION
            , example = DEPARTAMENTO_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = DEPARTAMENTO_CONTROLLER_UPDATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            , description = DEPARTAMENTO_CONTROLLER_400_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
            , description = DEPARTAMENTO_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = DEPARTAMENTO_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            , description = DEPARTAMENTO_CONTROLLER_409_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<RespostaDepartamentoDTO> atualizaDepartamento(String id
            , RequisicaoDepartamentoDTO requisicaoDepartamentoDTO
            , BindingResult bindingResult);

    @Tag(name=TAG_DELETE)
    @Operation(summary = DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY
            , description = DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403"
            , description = DEPARTAMENTO_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = DEPARTAMENTO_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            , description = DEPARTAMENTO_CONTROLLER_409_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<Void> deletarDepartamento(String id);
}