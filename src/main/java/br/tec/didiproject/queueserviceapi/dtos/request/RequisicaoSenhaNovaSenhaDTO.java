package br.tec.didiproject.queueserviceapi.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoSenhaNovaSenhaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_FILA_ID_TITLE
            , description = SCHEMA_SENHA_FILA_ID_DESCRIPTION
            , example = SCHEMA_SENHA_FILA_ID_EXAMPLE)
    @NotBlank(message = SENHA_NOVA_SENHA_FILA_ID_NOT_BLANK)
    @UUID(message = SENHA_NOVA_SENHA_FILA_ID_UUID)
    @JsonProperty(FILA_ID)
    private String filaId;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_TITLE
            , description = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_DESCRIPTION
            , example = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_EXAMPLE)
    @NotBlank(message = SENHA_NOVA_SENHA_TIPO_ATENDIMENTO_ID_NOT_BLANK)
    @UUID(message = SENHA_NOVA_SENHA_TIPO_ATENDIMENTO_ID_UUID)
    @JsonProperty(TIPO_ATENDIMENTO_ID)
    private String tipoAtendimentoId;
    @Schema(type = SCHEMA_TYPE_INTEGER
            , title = SCHEMA_SENHA_RESET_TITLE
            , description = SCHEMA_SENHA_RESET_DESCRIPTION
            , example = SCHEMA_SENHA_RESET_EXAMPLE
            , minimum = "1"
            , maximum = "32766")
    @Min(value = 1, message = SENHA_RESET_MIN)
    @Max(value = 32766, message = SENHA_RESET_MAX)
    @JsonProperty(RESET)
    private Short reset;
}
