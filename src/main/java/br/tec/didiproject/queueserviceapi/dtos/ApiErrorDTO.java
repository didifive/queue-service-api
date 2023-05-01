package br.tec.didiproject.queueserviceapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_ERROR_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_ERROR_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_ERROR_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_MESSAGE_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_MESSAGE_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_MESSAGE_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_PATH_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_PATH_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_PATH_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_STATUS_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_STATUS_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_STATUS_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_TIMESTAMP_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_TIMESTAMP_EXAMPLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_API_ERROR_TIMESTAMP_TITLE;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8591558767758850114L;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_API_ERROR_TIMESTAMP_TITLE
            , description = SCHEMA_API_ERROR_TIMESTAMP_DESCRIPTION
            , example = SCHEMA_API_ERROR_TIMESTAMP_EXAMPLE)
    private Instant timestamp;
    @Schema(type = SCHEMA_TYPE_INTEGER
            , title = SCHEMA_API_ERROR_STATUS_TITLE
            , description = SCHEMA_API_ERROR_STATUS_DESCRIPTION
            , example = SCHEMA_API_ERROR_STATUS_EXAMPLE)
    private Integer status;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_API_ERROR_ERROR_TITLE
            , description = SCHEMA_API_ERROR_ERROR_DESCRIPTION
            , example = SCHEMA_API_ERROR_ERROR_EXAMPLE)
    private String error;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_API_ERROR_MESSAGE_TITLE
            , description = SCHEMA_API_ERROR_MESSAGE_DESCRIPTION
            , example = SCHEMA_API_ERROR_MESSAGE_EXAMPLE)
    private String message;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_API_ERROR_PATH_TITLE
            , description = SCHEMA_API_ERROR_PATH_DESCRIPTION
            , example = SCHEMA_API_ERROR_PATH_EXAMPLE)
    private String path;

}