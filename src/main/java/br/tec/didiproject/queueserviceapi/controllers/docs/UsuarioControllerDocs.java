package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioAtualizarSenhaDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioNovoNomeUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioPerfilDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaUsuarioDTO;
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
@Tag(name = USUARIO_CONTROLER_TAG)
public interface UsuarioControllerDocs {

    @Tag(name = TAG_POST)
    @Operation(summary = USUARIO_CONTROLLER_CREATE_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "201"
            , description = USUARIO_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    ResponseEntity<RespostaUsuarioDTO> novoUsuario(
            RequisicaoUsuarioDTO requisicaoUsuarioDTO
            , BindingResult bindingResult);

    @Tag(name = TAG_GET)
    @Operation(summary = USUARIO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
    @PageParams
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse403
    ResponseEntity<Page<RespostaUsuarioDTO>> listarUsuarios(
            Pageable pageable
    );

    @Tag(name = TAG_GET)
    @Operation(summary = USUARIO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaUsuarioDTO> findById(String id);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_NOVO_NOME_USUARIO_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_NOVO_NOME_USUARIO_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_NOVO_NOME_USUARIO_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_NOVO_NOME_USUARIO_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_NOVO_NOME_USUARIO_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaUsuarioDTO> novoNomeUsuario(String id
            , RequisicaoUsuarioNovoNomeUsuarioDTO requisicaoUsuarioNovoNomeUsuarioDTO);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_ATUALIZAR_SENHA_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_ATUALIZAR_SENHA_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_ATUALIZAR_SENHA_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_ATUALIZAR_SENHA_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = USUARIO_CONTROLLER_ATUALIZAR_SENHA_204_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaUsuarioDTO> atualizarSenha(String id
            , RequisicaoUsuarioAtualizarSenhaDTO requisicaoUsuarioAtualizarSenhaDTO);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_ADICIONAR_PERFIL_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_ADICIONAR_PERFIL_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_ADICIONAR_PERFIL_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_ADICIONAR_PERFIL_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_ADICIONAR_PERFIL_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaUsuarioDTO> adicionarPerfil(String id
            , RequisicaoUsuarioPerfilDTO requisicaoUsuarioPerfilDTO);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_REMOVER_PERFIL_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_REMOVER_PERFIL_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_REMOVER_PERFIL_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_REMOVER_PERFIL_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_REMOVER_PERFIL_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    @ApiResponse409
    ResponseEntity<RespostaUsuarioDTO> removerPerfil(String id
            , RequisicaoUsuarioPerfilDTO requisicaoUsuarioPerfilDTO);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_ATIVAR_USUARIO_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_ATIVAR_USUARIO_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_ATIVAR_USUARIO_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_ATIVAR_USUARIO_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_ATIVAR_USUARIO_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaUsuarioDTO> ativarUsuario(String id);

    @Tag(name = TAG_PATCH)
    @Operation(summary = USUARIO_CONTROLLER_DESATIVAR_USUARIO_OPERATION_SUMMARY
            , description = USUARIO_CONTROLLER_DESATIVAR_USUARIO_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "id"
            , description = USUARIO_CONTROLLER_DESATIVAR_USUARIO_PARAMETER_ID_DESCRIPTION
            , example = USUARIO_CONTROLLER_DESATIVAR_USUARIO_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = USUARIO_CONTROLLER_DESATIVAR_USUARIO_200_DESCRIPTION)
    @ApiResponse400
    @ApiResponse403
    @ApiResponse404
    ResponseEntity<RespostaUsuarioDTO> desativarUsuario(String id);
}