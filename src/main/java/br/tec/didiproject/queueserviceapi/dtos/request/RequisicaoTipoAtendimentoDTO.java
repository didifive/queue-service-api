package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoTipoAtendimentoDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_NOME_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_NOME_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = TIPO_ATENDIMENTO_NOME_NOT_BLANK)
    @Size(min = 3, max = 255, message = TIPO_ATENDIMENTO_NOME_SIZE)
    @JsonProperty(NOME)
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_SIGLA_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_SIGLA_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_SIGLA_EXAMPLE
            , minLength = 1
            , maxLength = 3)
    @NotBlank(message = TIPO_ATENDIMENTO_SIGLA_NOT_BLANK)
    @Pattern(regexp = SIGLA_PATTERN, message = TIPO_ATENDIMENTO_SIGLA_PATTERN)
    @Size(min = 1, max = 3, message = TIPO_ATENDIMENTO_SIGLA_SIZE)
    @JsonProperty(SIGLA)
    String sigla;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_EXAMPLE
            , minimum = "1"
            , maximum = "32767")
    @NotNull(message = TIPO_ATENDIMENTO_PRIORIDADE_NOT_NULL)
    @Min(value = 1, message = TIPO_ATENDIMENTO_PRIORIDADE_MIN)
    @Max(value = 32767, message = TIPO_ATENDIMENTO_PRIORIDADE_MAX)
    @JsonProperty(PRIORIDADE)
    Short prioridade;
}
