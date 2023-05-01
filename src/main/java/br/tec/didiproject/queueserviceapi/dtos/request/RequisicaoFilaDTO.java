package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;
import java.util.List;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoFilaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_NOME_TITLE
            , description = SCHEMA_FILA_NOME_DESCRIPTION
            , example = SCHEMA_FILA_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = FILA_NOME_NOT_BLANK)
    @Size(min = 3, max = 255, message = FILA_NOME_SIZE)
    @JsonProperty(NOME)
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_SIGLA_TITLE
            , description = SCHEMA_FILA_SIGLA_DESCRIPTION
            , example = SCHEMA_FILA_SIGLA_EXAMPLE
            , minLength = 1
            , maxLength = 3)
    @NotBlank(message = FILA_SIGLA_NOT_BLANK)
    @Pattern(regexp = SIGLA_PATTERN, message = FILA_SIGLA_PATTERN)
    @Size(min = 1, max = 3, message = FILA_SIGLA_SIZE)
    @JsonProperty(SIGLA)
    String sigla;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_DEPARTAMENTO_ID_TITLE
            , description = SCHEMA_FILA_DEPARTAMENTO_ID_DESCRIPTION
            , example = SCHEMA_FILA_DEPARTAMENTO_ID_EXAMPLE)
    @NotBlank(message = FILA_DEPARTAMENTO_ID_NOT_BLANK)
    @UUID(message = FILA_DEPARTAMENTO_ID_UUID)
    @JsonProperty(DEPARTAMENTO_ID)
    String departamentoId;
    @ArraySchema(schema =
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_TITLE
            , description = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_DESCRIPTION
            , example = SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_EXAMPLE)
            , uniqueItems = true)
    @JsonProperty(TIPOS_ATENDIMENTO_ID)
    transient List<@UUID(message = FILA_TIPOS_ATENDIMENTO_UUID) String> tiposAtendimentoId;
}
