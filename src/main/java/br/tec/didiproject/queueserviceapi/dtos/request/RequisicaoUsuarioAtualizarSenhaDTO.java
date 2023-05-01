package br.tec.didiproject.queueserviceapi.dtos.request;

import br.tec.didiproject.queueserviceapi.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_BOOLEAN;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.NOVA_SENHA;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.SENHA_ATUAL;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ValidationMessagesV1.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoUsuarioAtualizarSenhaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_SENHA_TITLE
            , description = SCHEMA_USUARIO_SENHA_DESCRIPTION
            , example = SCHEMA_USUARIO_SENHA_EXAMPLE
            , minLength = 6
            , maxLength = 60)
    @NotBlank(message = USUARIO_ATUALIZAR_SENHA_SENHA_ATUAL_NOT_BLANK)
    @Size(min = 6, max = 60, message = USUARIO_ATUALIZAR_SENHA_SENHA_ATUAL_SIZE)
    @JsonProperty(SENHA_ATUAL)
    String senhaAtual;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_SENHA_TITLE
            , description = SCHEMA_USUARIO_SENHA_DESCRIPTION
            , example = SCHEMA_USUARIO_SENHA_EXAMPLE
            , minLength = 6
            , maxLength = 60)
    @NotBlank(message = USUARIO_ATUALIZAR_SENHA_NOVA_SENHA_NOT_BLANK)
    @Size(min = 6, max = 60, message = USUARIO_ATUALIZAR_SENHA_NOVA_SENHA_SIZE)
    @JsonProperty(NOVA_SENHA)
    String novaSenha;

}
