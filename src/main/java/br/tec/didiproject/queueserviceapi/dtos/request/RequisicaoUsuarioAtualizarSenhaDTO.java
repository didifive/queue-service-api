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
    @NotBlank(message = "Informe a senha atual")
    @Size(min = 6, max = 60, message = "A senha atual deve ter entre 6 e 60 caracteres")
    @JsonProperty("senhaAtual")
    String senhaAtual;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_SENHA_TITLE
            , description = SCHEMA_USUARIO_SENHA_DESCRIPTION
            , example = SCHEMA_USUARIO_SENHA_EXAMPLE
            , minLength = 6
            , maxLength = 60)
    @NotBlank(message = "Informe a nova senha")
    @Size(min = 6, max = 60, message = "A nova senha deve ter entre 6 e 60 caracteres")
    @JsonProperty("novaSenha")
    String novaSenha;

}
