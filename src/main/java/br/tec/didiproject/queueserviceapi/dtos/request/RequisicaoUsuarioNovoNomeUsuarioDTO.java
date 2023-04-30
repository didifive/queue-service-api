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
public class RequisicaoUsuarioNovoNomeUsuarioDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_NOME_TITLE
            , description = SCHEMA_USUARIO_NOME_DESCRIPTION
            , example = SCHEMA_USUARIO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @NotBlank(message = "Informe um nome de usuário")
    @Size(min = 3, max = 255, message = "O nome do usuário deve ter entre 3 e 255 caracteres")
    @JsonProperty("nomeUsuario")
    String nomeUsuario;
}
