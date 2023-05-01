package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class RequisicaoEmpresaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_NOME_TITLE
            , description = SCHEMA_EMPRESA_NOME_DESCRIPTION
            , example = SCHEMA_EMPRESA_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = EMPRESA_NOME_NOT_BLANK)
    @Size(min = 3, max = 255, message = EMPRESA_NOME_SIZE)
    @JsonProperty(NOME)
    private String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_CPF_CNPJ_TITLE
            , description = SCHEMA_EMPRESA_CPF_CNPJ_DESCRIPTION
            , example = SCHEMA_EMPRESA_CPF_CNPJ_EXAMPLE
            , minLength = 11
            , maxLength = 18)
    @Size(min = 11, max = 18, message = EMPRESA_CPF_CNPJ_SIZE)
    @JsonProperty(CPF_CNPJ)
    private String cpfCnpj;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_ENDERECO_TITLE
            , description = SCHEMA_EMPRESA_ENDERECO_DESCRIPTION
            , example = SCHEMA_EMPRESA_ENDERECO_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @Size(min = 3, max = 255, message = EMPRESA_ENDERECO_SIZE)
    @JsonProperty(ENDERECO)
    private String endereco;
}
