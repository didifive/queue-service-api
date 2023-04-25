package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SCHEMA_DEPARTAMENTO_NOME_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoDepartamentoDTO implements Serializable {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_NOME_TITLE
            , description = SCHEMA_DEPARTAMENTO_NOME_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank
    @Size(min = 3, max = 255)
    @JsonProperty("nome")
    String nome;

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_EMPRESA_ID_TITLE
            , description = SCHEMA_DEPARTAMENTO_EMPRESA_ID_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_EMPRESA_ID_EXAMPLE)
    @NotBlank
    @UUID
    String empresaId;
}
