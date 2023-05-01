package br.tec.didiproject.queueserviceapi.controllers.docs;

import br.tec.didiproject.queueserviceapi.dtos.request.AuthDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RefreshTokenResponseDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@Tag(name = AUTH_CONTROLER_TAG)
public interface AuthControllerDocs {
    @Tag(name = TAG_POST)
    @Operation(summary = AUTH_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY
            , description = AUTH_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "200"
            , description = AUTH_CONTROLER_AUTHENTICATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "401"
            , description = AUTH_CONTROLER_AUTHENTICATE_401_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<TokenResponseDTO> authenticate(AuthDTO authDto);

    @Tag(name = TAG_POST)
    @Operation(summary = AUTH_CONTROLER_REFRESH_TOKEN_OPERATION_SUMMARY
            , description = AUTH_CONTROLER_REFRESH_TOKEN_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "usuarioId"
            , description = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_DESCRIPTION
            , example = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "refreshToken"
            , description = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_REFRESH_TOKEN_DESCRIPTION
            , example = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_REFRESH_TOKEN_EXAMPLE)
    @ApiResponse(responseCode = "200"
            , description = AUTH_CONTROLER_REFRESH_TOKEN_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = AUTH_CONTROLER_REFRESH_TOKEN_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = AUTH_CONTROLER_REFRESH_TOKEN_404_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<RefreshTokenResponseDTO> refreshToken(String usuarioId, String refreshToken);

    @SecurityRequirement(name = SECURITY_SCHEME_NAME)
    @Tag(name = TAG_DELETE)
    @Operation(summary = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_OPERATION_SUMMARY
            , description = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_OPERATION_DESCRIPTION)
    @Parameter(in = ParameterIn.PATH
            , schema = @Schema(type = SCHEMA_TYPE_STRING)
            , name = "usuarioId"
            , description = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_PARAMETER_USUARIO_ID_DESCRIPTION
            , example = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
            , description = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_204_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            , description = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_403_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404"
            , description = AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_404_DESCRIPTION
            , content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Void> invalidaRefreshToken(String usuarioId);
}
