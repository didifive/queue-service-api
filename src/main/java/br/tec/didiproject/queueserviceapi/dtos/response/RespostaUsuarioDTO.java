package br.tec.didiproject.queueserviceapi.dtos.response;

import br.tec.didiproject.queueserviceapi.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;
import java.util.List;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_BOOLEAN;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;

@Data
@Builder
@AllArgsConstructor
public class RespostaUsuarioDTO implements Serializable {

    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_ID_TITLE
            , description = SCHEMA_USUARIO_ID_DESCRIPTION
            , example = SCHEMA_USUARIO_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty(ID)
    String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_NOME_TITLE
            , description = SCHEMA_USUARIO_NOME_DESCRIPTION
            , example = SCHEMA_USUARIO_NOME_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty(NOME_USUARIO)
    String nomeUsuario;
    @ArraySchema(schema =
    @Schema(implementation = Perfil.class
            , title = SCHEMA_USUARIO_PERFIL_TITLE
            , description = SCHEMA_USUARIO_PERFIL_DESCRIPTION
            , example = SCHEMA_USUARIO_PERFIL_EXAMPLE)
            , uniqueItems = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(PERFIS)
    transient List<Perfil> perfis;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_USUARIO_ATENDENTE_ID_TITLE
            , description = SCHEMA_USUARIO_ATENDENTE_ID_DESCRIPTION
            , example = SCHEMA_USUARIO_ATENDENTE_ID_EXAMPLE
            , nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @UUID
    @JsonProperty(ATENDENTE_ID)
    String atendenteId;
    @Schema(type = SCHEMA_TYPE_BOOLEAN
            , title = SCHEMA_USUARIO_ATIVO_TITLE
            , description = SCHEMA_USUARIO_ATIVO_DESCRIPTION
            , example = SCHEMA_USUARIO_ATIVO_EXAMPLE
            , defaultValue = "true")
    @JsonProperty(ATIVO)
    Boolean ativo;
}
