package br.tec.didiproject.queueserviceapi.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Builder
@AllArgsConstructor
public class RespostaEmpresaDTO implements Serializable {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_ID_TITLE
            , description = SCHEMA_EMPRESA_ID_DESCRIPTION
            , example = SCHEMA_EMPRESA_ID_EXAMPLE
            , maxLength = 36)
    @JsonProperty("id")
    String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_NOME_TITLE
            , description = SCHEMA_EMPRESA_NOME_DESCRIPTION
            , example = SCHEMA_EMPRESA_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty("nome")
    String nome;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_CPF_CNPJ_TITLE
            , description = SCHEMA_EMPRESA_CPF_CNPJ_DESCRIPTION
            , example = SCHEMA_EMPRESA_CPF_CNPJ_EXAMPLE
            , minLength = 11
            , maxLength = 18
            , nullable = true)
    @JsonInclude(value = Include.NON_NULL)
    @JsonProperty("cpfCnpj")
    String cpfCnpj;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_EMPRESA_ENDERECO_TITLE
            , description = SCHEMA_EMPRESA_ENDERECO_DESCRIPTION
            , example = SCHEMA_EMPRESA_ENDERECO_EXAMPLE
            , minLength = 3
            , maxLength = 255
            , nullable = true)
    @JsonInclude(value = Include.NON_NULL)
    @JsonProperty("endereco")
    String endereco;
}