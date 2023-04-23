package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoAtendenteDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaAtendenteDTO;
import br.tec.didiproject.queueserviceapi.utils.annotations.swagger.*;
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
@Tag(name = ATENDENTE_CONTROLER_TAG)
public interface AtendenteControllerDocs {

    @Tag(name = TAG_POST)
    @Operation(summary = ATENDENTE_CONTROLLER_CREATE_OPERATION_SUMMARY
            , description = ATENDENTE_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "201"
            , description = ATENDENTE_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    ResponseEntity<RespostaAtendenteDTO> novoAtendente(
            RequisicaoAtendenteDTO requisicaoAtendenteDTO
            , BindingResult bindingResult);

    @Tag(name = TAG_GET)
    @Operation(summary = ATENDENTE_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
            , description = ATENDENTE_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
    @PageParams
    @ApiResponse(responseCode = "200"
            , description = ATENDENTE_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse403
    ResponseEntity<Page<RespostaAtendenteDTO>> listarAtendentes(
            Pageable pageable
    );

    @Tag(name = TAG_GET)
    @Operation(summary = ATENDENTE_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
            , description = ATENDENTE_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = ATENDENTE_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaAtendenteDTO> findById(String id);

    @Tag(name = TAG_PUT)
    @Operation(summary = ATENDENTE_CONTROLLER_UPDATE_OPERATION_SUMMARY
            , description = ATENDENTE_CONTROLLER_UPDATE_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = ATENDENTE_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION
            , example = ATENDENTE_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = ATENDENTE_CONTROLLER_UPDATE_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaAtendenteDTO> atualizaAtendente(String id
            , RequisicaoAtendenteDTO requisicaoAtendenteDTO
            , BindingResult bindingResult);

    @Tag(name = TAG_DELETE)
    @Operation(summary = ATENDENTE_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY
            , description = ATENDENTE_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = ATENDENTE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = ATENDENTE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = ATENDENTE_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<Void> deletarAtendente(String id);
}