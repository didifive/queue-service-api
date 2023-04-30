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
public class RequisicaoUsuarioDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_NOME_TITLE
            , description = SCHEMA_USUARIO_NOME_DESCRIPTION
            , example = SCHEMA_USUARIO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = "Informe um nome de usuário")
    @Size(min = 3, max = 255, message = "O nome de usuário deve ter entre 3 e 255 caracteres")
    @JsonProperty("nomeUsuario")
    String nomeUsuario;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_SENHA_TITLE
            , description = SCHEMA_USUARIO_SENHA_DESCRIPTION
            , example = SCHEMA_USUARIO_SENHA_EXAMPLE
            , minLength = 6
            , maxLength = 60)
    @NotBlank(message = "Informe uma senha para o usuário")
    @Size(min = 6, max = 60, message = "A senha deve ter entre 6 e 60 caracteres")
    @JsonProperty("senha")
    String senha;
    @ArraySchema(schema =
    @Schema(implementation = Perfil.class
            , title = SCHEMA_USUARIO_PERFIL_TITLE
            , description = SCHEMA_USUARIO_PERFIL_DESCRIPTION
            , example = SCHEMA_USUARIO_PERFIL_EXAMPLE)
        , uniqueItems = true)
    @JsonProperty("perfis")
    @Valid
    List<Perfil> perfis;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_ATENDENTE_ID_TITLE
            , description = SCHEMA_USUARIO_ATENDENTE_ID_DESCRIPTION
            , example = SCHEMA_USUARIO_ATENDENTE_ID_EXAMPLE)
    @UUID(message = "O Id informado para o atendente está inválido")
    @JsonProperty("atendenteId")
    String atendenteId;
    @Schema(type = SCHEMA_TYPE_BOOLEAN
            , title = SCHEMA_USUARIO_ATIVO_TITLE
            , description = SCHEMA_USUARIO_ATIVO_DESCRIPTION
            , example = SCHEMA_USUARIO_ATIVO_EXAMPLE
            , defaultValue = "true")
    @JsonProperty("ativo")
    Boolean ativo;

}
