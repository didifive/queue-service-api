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
    @NotBlank
    @Size(min = 3, max = 255)
    @JsonProperty("nome")
    String nome;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_CPF_CNPJ_TITLE
            , description = SCHEMA_EMPRESA_CPF_CNPJ_DESCRIPTION
            , example = SCHEMA_EMPRESA_CPF_CNPJ_EXAMPLE
            , minLength = 11
            , maxLength = 18)
    @Size(min = 11, max = 18)
    @JsonProperty("cpfCnpj")
    String cpfCnpj;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_ENDERECO_TITLE
            , description = SCHEMA_EMPRESA_ENDERECO_DESCRIPTION
            , example = SCHEMA_EMPRESA_ENDERECO_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @Size(min = 3, max = 255)
    @JsonProperty("endereco")
    String endereco;
}
