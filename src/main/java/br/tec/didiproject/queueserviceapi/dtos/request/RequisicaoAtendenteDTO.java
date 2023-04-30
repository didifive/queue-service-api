package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoAtendenteDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_NOME_TITLE
            , description = SCHEMA_ATENDENTE_NOME_DESCRIPTION
            , example = SCHEMA_ATENDENTE_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = "Informe um nome para o atendente")
    @Size(min = 3, max = 255, message = "O nome do atendente deve ter entre 3 e 255 caracteres")
    @JsonProperty("nome")
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_EMAIL_TITLE
            , description = SCHEMA_ATENDENTE_EMAIL_DESCRIPTION
            , example = SCHEMA_ATENDENTE_EMAIL_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = "Informe um email para o atendente")
    @Email(message = "Informe um e-mail válido para o atendente")
    @Size(min = 3, max = 255, message = "O e-mail do atendente deve ter entre 3 e 255 caracteres")
    @JsonProperty("email")
    String email;
    @ArraySchema(schema =
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_TITLE
            , description = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_DESCRIPTION
            , example = SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_EXAMPLE)
            , uniqueItems = true)
    @JsonProperty("departamentosId")
    @UUID(message = "O(s) Id(s) informado(s) para o(s) departamento(s) está(ão) inválido(s)")
    List<String> departamentosId;
}
