package br.tec.didiproject.queueserviceapi.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;

@Data
@Builder
@AllArgsConstructor
public class RespostaAtendenteDTO implements Serializable {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_ID_TITLE
            , description = SCHEMA_DEPARTAMENTO_ID_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty("id")
    String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_NOME_TITLE
            , description = SCHEMA_DEPARTAMENTO_NOME_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty("nome")
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_NOME_TITLE
            , description = SCHEMA_DEPARTAMENTO_NOME_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty("nome")
    String email;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_EMPRESA_ID_TITLE
            , description = SCHEMA_DEPARTAMENTO_EMPRESA_ID_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_EMPRESA_ID_EXAMPLE)
    @JsonProperty("departamentosId")
    List<String> departamentosId;
}
