package br.tec.didiproject.queueserviceapi.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.*;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.JsonPropertyDTOs.*;

@Data
@Builder
@AllArgsConstructor
public class RespostaSenhaDTO implements Serializable {
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_ID_TITLE
            , description = SCHEMA_SENHA_ID_DESCRIPTION
            , example = SCHEMA_SENHA_ID_EXAMPLE
            , minLength = 36
            , maxLength = 36)
    @JsonProperty(ID)
    private String id;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_NUMERO_TITLE
            , description = SCHEMA_SENHA_NUMERO_DESCRIPTION
            , example = SCHEMA_SENHA_NUMERO_EXAMPLE
            , minLength = 3
            , maxLength = 255)
    @JsonProperty(NUMERO)
    private Short numero;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_FILA_ID_TITLE
            , description = SCHEMA_SENHA_FILA_ID_DESCRIPTION
            , example = SCHEMA_SENHA_FILA_ID_EXAMPLE)
    @JsonProperty(FILA_ID)
    private String filaId;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_TITLE
            , description = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_DESCRIPTION
            , example = SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_EXAMPLE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(TIPOS_ATENDIMENTO_ID)
    private String tipoAtendimentoId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(GERADA_EM)
    private Timestamp geradaEm;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(CHAMADA_EM)
    private Timestamp chamadaEm;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(FINALIZADA_EM)
    private Timestamp finalizadaEm;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(MOTIVO_FINALIZADA)
    private String motivoFinalizada;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(ATENDIDA_EM)
    private Timestamp atendidaEm;
    @Schema(type = SCHEMA_TYPE_STRING
            , title = SCHEMA_SENHA_ATENDENTE_ID_TITLE
            , description = SCHEMA_SENHA_ATENDENTE_ID_DESCRIPTION
            , example = SCHEMA_SENHA_ATENDENTE_ID_EXAMPLE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(ATENDENTE_ID)
    private String atendenteId;
    @JsonProperty(FOI_CHAMADA)
    private Boolean foiChamada;
    @JsonProperty(FOI_ATENDIDA)
    private Boolean foiAtendida;
    @JsonProperty(FOI_FINALIZADA)
    private Boolean foiFinalizada;
}
