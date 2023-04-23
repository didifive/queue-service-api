package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.ApiErrorDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoEmpresaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaEmpresaDTO;
import br.tec.didiproject.queueserviceapi.utils.annotations.PageParams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
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
            , description = EMPRESA_CONTROLLER_400_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<RespostaEmpresaDTO> novaEmpresa(
            RequisicaoEmpresaDTO requisicaoEmpresaDTO
            , BindingResult bindingResult);

    @Tag(name=TAG_GET)
    @Operation(summary = EMPRESA_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
    @PageParams
    @ApiResponse(responseCode = "200"
            , description = EMPRESA_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<RespostaEmpresaDTO>> listarEmpresas(
            Pageable pageable
    );

    @Tag(name=TAG_GET)
    @Operation(summary = EMPRESA_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = EMPRESA_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = EMPRESA_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<RespostaEmpresaDTO> findById(String id);

    @Tag(name=TAG_PUT)
    @Operation(summary = EMPRESA_CONTROLLER_UPDATE_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_UPDATE_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = EMPRESA_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION
            , example = EMPRESA_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = EMPRESA_CONTROLLER_UPDATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            , description = EMPRESA_CONTROLLER_400_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = EMPRESA_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            , description = EMPRESA_CONTROLLER_409_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<RespostaEmpresaDTO> atualizaEmpresa(String id
            , RequisicaoEmpresaDTO requisicaoEmpresaDTO
            , BindingResult bindingResult);

    @Tag(name=TAG_DELETE)
    @Operation(summary = EMPRESA_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY
            , description = EMPRESA_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = EMPRESA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = EMPRESA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = EMPRESA_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403"
            , description = EMPRESA_CONTROLLER_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = EMPRESA_CONTROLLER_404_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            , description = EMPRESA_CONTROLLER_409_DESCRIPTION
            , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<Void> deletarEmpresa(String id);
}