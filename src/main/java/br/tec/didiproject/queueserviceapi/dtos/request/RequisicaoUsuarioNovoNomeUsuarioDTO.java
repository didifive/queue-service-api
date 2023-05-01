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
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.NOME_USUARIO;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.USUARIO_NOME_USUARIO_NOT_BLANK;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.USUARIO_NOME_USUARIO_SIZE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoUsuarioNovoNomeUsuarioDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_NOME_TITLE
            , description = SCHEMA_USUARIO_NOME_DESCRIPTION
            , example = SCHEMA_USUARIO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = USUARIO_NOME_USUARIO_NOT_BLANK)
    @Size(min = 3, max = 255, message = USUARIO_NOME_USUARIO_SIZE)
    @JsonProperty(NOME_USUARIO)
    String nomeUsuario;
}
