package br.tec.didiproject.queueserviceapi.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;

@Data
@AllArgsConstructor
public class RefreshTokenResponseDTO {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_AUTH_TOKEN_TITLE
            , description = SCHEMA_AUTH_TOKEN_DESCRIPTION
            , example = SCHEMA_AUTH_TOKEN_EXAMPLE)
    private String token;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_AUTH_REFRESH_TOKEN_TITLE
            , description = SCHEMA_AUTH_REFRESH_TOKEN_DESCRIPTION
            , example = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE)
    private String refreshToken;

}
