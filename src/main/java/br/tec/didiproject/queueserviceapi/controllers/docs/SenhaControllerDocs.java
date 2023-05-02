package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaFinalizaSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoSenhaNovaSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaSenhaDTO;
import br.tec.didiproject.queueserviceapi.utils.annotations.swagger.ApiResponse400;
import br.tec.didiproject.queueserviceapi.utils.annotations.swagger.ApiResponse403;
import br.tec.didiproject.queueserviceapi.utils.annotations.swagger.ApiResponse404;
import br.tec.didiproject.queueserviceapi.utils.annotations.swagger.ApiResponse409;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_BOOLEAN;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@SecurityRequirement(name = SECURITY_SCHEME_NAME)
@Tag(name = SENHA_CONTROLER_TAG)
public interface SenhaControllerDocs {

    @Tag(name = TAG_POST)
    @Operation(summary = SENHA_CONTROLLER_CREATE_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "201"
            , description = SENHA_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaSenhaDTO> novaSenha(
            RequisicaoSenhaNovaSenhaDTO novaSenhaDTO
            , BindingResult bindingResult
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_CHAMAR_SENHA_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @Parameter(in = ParameterIn.QUERY
            , schema = @Schema(type = SCHEMA_TYPE_BOOLEAN)
            , name = "rechamada"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_RECHAMADA_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_RECHAMADA_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaSenhaDTO> chamarSenha(
            String id
            , Boolean rechamada
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_CHAMAR_PROXIMA_SENHA_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_CHAMAR_PROXIMA_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "filaId"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = SENHA_CONTROLLER_CHAMAR_PROXIMA_SENHA_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaSenhaDTO> chamarProximaSenha(
            @PathVariable String filaId
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_FINALIZAR_SENHA_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_FINALIZAR_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = SENHA_CONTROLLER_FINALIZAR_SENHA_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaSenhaDTO> finalizarSenha(
            String id
            , RequisicaoSenhaFinalizaSenhaDTO requisicaoSenhaFinalizaSenhaDTO
            , BindingResult bindingResult
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_FINALIZAR_SENHA_POR_FILA_E_TIPO_ATENDIMENTO_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_FINALIZAR_SENHA_POR_FILA_E_TIPO_ATENDIMENTO_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = SENHA_CONTROLLER_FINALIZAR_SENHA_POR_FILA_E_TIPO_ATENDIMENTO_204_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<Void> finalizarSenhaPorFilaETipoAtendimento(
            RequisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO requisicaoSenhaFinalizaPorFilaETipoAtendimentoDTO
            , BindingResult bindingResult
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_ATENDER_SENHA_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_ATENDER_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @Parameter(name = "authentication"
            , hidden = true)
    @ApiResponse(responseCode = "200"
            , description = SENHA_CONTROLLER_ATENDER_SENHA_200_DESCRIPTION)
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaSenhaDTO> atenderSenha(
            String id
            , Authentication authentication
    );

    @Tag(name = TAG_PATCH)
    @Operation(summary = SENHA_CONTROLLER_RESETAR_STATUS_SENHA_OPERATION_SUMMARY
            , description = SENHA_CONTROLLER_RESETAR_STATUS_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_DESCRIPTION
            , example = SENHA_CONTROLLER_CHAMAR_SENHA_PARAMETER_FILA_ID_EXAMPLE)
    @Parameter(name = "authentication"
            , hidden = true)
    @ApiResponse(responseCode = "200"
            , description = SENHA_CONTROLLER_RESETAR_STATUS_SENHA_200_DESCRIPTION)
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaSenhaDTO> resetarStatusSenha(
            String id
    );
}