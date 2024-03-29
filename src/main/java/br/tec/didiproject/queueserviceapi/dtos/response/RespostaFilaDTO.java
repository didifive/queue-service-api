package br.tec.didiproject.queueserviceapi.dtos.response;

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
public class RespostaFilaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_ID_TITLE
            , description = SCHEMA_FILA_ID_DESCRIPTION
            , example = SCHEMA_FILA_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty(ID)
    private String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_NOME_TITLE
            , description = SCHEMA_FILA_NOME_DESCRIPTION
            , example = SCHEMA_FILA_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty(NOME)
    private String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_SIGLA_TITLE
            , description = SCHEMA_FILA_SIGLA_DESCRIPTION
            , example = SCHEMA_FILA_SIGLA_EXAMPLE
            , minLength = 1
            , maxLength = 3)
    @JsonProperty(SIGLA)
    private String sigla;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_DEPARTAMENTO_ID_TITLE
            , description = SCHEMA_FILA_DEPARTAMENTO_ID_DESCRIPTION
            , example = SCHEMA_FILA_DEPARTAMENTO_ID_EXAMPLE)
    @JsonProperty(DEPARTAMENTO_ID)
    private String departamentoId;
    @ArraySchema(schema =
    @Schema(implementation = String.class
            , title = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_TITLE
            , description = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_DESCRIPTION
            , example = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_EXAMPLE)
            , uniqueItems = true)
    @JsonProperty(TIPOS_ATENDIMENTO_ID)
    private List<String> tiposAtendimentoId;
}
