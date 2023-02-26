package br.tec.didiproject.queueserviceapi.dtos.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_TOKEN_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_TOKEN_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_TOKEN_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;

@Data
@AllArgsConstructor
public class TokenDTO {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_AUTH_TOKEN_TITLE
            , description = SCHEMA_AUTH_TOKEN_DESCRIPTION
            , example = SCHEMA_AUTH_TOKEN_EXAMPLE)
    private String token;

}
