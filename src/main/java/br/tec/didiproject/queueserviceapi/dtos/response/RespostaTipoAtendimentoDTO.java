package br.tec.didiproject.queueserviceapi.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@AllArgsConstructor
public class RespostaTipoAtendimentoDTO implements Serializable {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_ID_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_ID_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty("id")
    String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_NOME_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_NOME_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty("nome")
    String nome;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_SIGLA_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_SIGLA_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_SIGLA_EXAMPLE
            , minLength = 1
            , maxLength = 3)
    @JsonProperty("sigla")
    String sigla;

    @Schema(type = SCHEMA_TYPE_INTEGER
            , title = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_EXAMPLE
            , minimum = "1"
            , maximum = "32767")
    @JsonProperty("prioridade")
    Short prioridade;
}
