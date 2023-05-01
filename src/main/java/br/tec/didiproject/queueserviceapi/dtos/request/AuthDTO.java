package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_PASSWORD_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_PASSWORD_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_PASSWORD_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_USERNAME_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_USERNAME_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_AUTH_USERNAME_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;

@Data
public class AuthDTO {

    @Schema(type = SCHEMA_TYPE_STRING
            ,title = SCHEMA_AUTH_USERNAME_TITLE
            , description = SCHEMA_AUTH_USERNAME_DESCRIPTION
            , example = SCHEMA_AUTH_USERNAME_EXAMPLE
            , maxLength = 100)
    @JsonProperty(USERNAME)
    private String username;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_AUTH_PASSWORD_TITLE
            , description = SCHEMA_AUTH_PASSWORD_DESCRIPTION
            , example = SCHEMA_AUTH_PASSWORD_EXAMPLE
            , maxLength = 70)
    @JsonProperty(PASSWORD)
    private String password;

}
