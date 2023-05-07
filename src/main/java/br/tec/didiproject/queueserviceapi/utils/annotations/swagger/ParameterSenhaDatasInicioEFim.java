package br.tec.didiproject.queueserviceapi.utils.annotations.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@Parameter(in = ParameterIn.PATH
        , schema = @Schema(type = SCHEMA_TYPE_STRING)
        , name = "dataInicio"
        , description = SENHA_CONTROLLER_PARAMETER_DATA_INICIO_DESCRIPTION
        , example = SENHA_CONTROLLER_PARAMETER_DATA_EXAMPLE)
@Parameter(in = ParameterIn.PATH
        , schema = @Schema(type = SCHEMA_TYPE_STRING)
        , name = "dataFim"
        , description = SENHA_CONTROLLER_PARAMETER_DATA_FIM_DESCRIPTION
        , example = SENHA_CONTROLLER_PARAMETER_DATA_EXAMPLE)
public @interface ParameterSenhaDatasInicioEFim {
}
