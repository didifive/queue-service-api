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
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.EMPRESA_ID;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.NOME;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.*;

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
    @NotBlank(message = DEPARTAMENTO_NOME_NOT_BLANK)
    @Size(min = 3, max = 255, message = DEPARTAMENTO_NOME_SIZE)
    @JsonProperty(NOME)
    String nome;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_DEPARTAMENTO_EMPRESA_ID_TITLE
            , description = SCHEMA_DEPARTAMENTO_EMPRESA_ID_DESCRIPTION
            , example = SCHEMA_DEPARTAMENTO_EMPRESA_ID_EXAMPLE)
    @NotBlank(message = DEPARTAMENTO_EMPRESA_ID_NOT_BLANK)
    @UUID(message = DEPARTAMENTO_EMPRESA_ID_UUID)
    @JsonProperty(EMPRESA_ID)
    String empresaId;
}
