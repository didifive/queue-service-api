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
    @NotBlank(message = "Informe um nome para o tipo de atendimento")
    @Size(min = 3, max = 255, message = "O nome do tipo de atendimento deve ter entre 3 e 255 caracteres")
    @JsonProperty("nome")
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_SIGLA_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_SIGLA_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_SIGLA_EXAMPLE
            , minLength = 1
            , maxLength = 3)
    @NotBlank(message = "Informe uma sigla para o tipo de atendimento")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "A sigla aceita somente caractere de texto de A até Z")
    @Size(min = 1, max = 3, message = "A sigla do tipo de atendimento deve ter entre 1 e 3 caracteres")
    @JsonProperty("sigla")
    String sigla;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_TITLE
            , description = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_DESCRIPTION
            , example = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_EXAMPLE
            , minimum = "1"
            , maximum = "32767")
    @NotNull(message = "Informe uma prioridade para o tipo de atendimento")
    @Min(value = 1, message = "O valor mínimo aceito para prioridade é [1]")
    @Max(value = 32767, message = "O valor máximo aceito para prioridade é [32767]")
    @JsonProperty("prioridade")
    Short prioridade;
}
