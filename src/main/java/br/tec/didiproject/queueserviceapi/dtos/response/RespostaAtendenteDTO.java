package br.tec.didiproject.queueserviceapi.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;

@Data
@Builder
@AllArgsConstructor
public class RespostaAtendenteDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_ID_TITLE
            , description = SCHEMA_ATENDENTE_ID_DESCRIPTION
            , example = SCHEMA_ATENDENTE_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty(ID)
    private String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_NOME_TITLE
            , description = SCHEMA_ATENDENTE_NOME_DESCRIPTION
            , example = SCHEMA_ATENDENTE_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty(NOME)
    private String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_EMAIL_TITLE
            , description = SCHEMA_ATENDENTE_EMAIL_DESCRIPTION
            , example = SCHEMA_ATENDENTE_EMAIL_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty(EMAIL)
    private String email;
    @ArraySchema(schema =
    @Schema(implementation = String.class
            , title = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_TITLE
            , description = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_DESCRIPTION
            , example = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_EXAMPLE)
            , uniqueItems = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(DEPARTAMENTOS_ID)
    private  List<String> departamentosId;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_USUARIO_ID_TITLE
            , description = SCHEMA_ATENDENTE_USUARIO_ID_DESCRIPTION
            , example = SCHEMA_ATENDENTE_USUARIO_ID_EXAMPLE
            , nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(USUARIO_ID)
    private String usuarioId;
}
