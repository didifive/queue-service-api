package br.tec.didiproject.queueserviceapi.utils.annotations.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.SENHA_CONTROLLER_PARAMETER_ID_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.SENHA_CONTROLLER_PARAMETER_ID_EXAMPLE;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.PATH
        , schema = @Schema(type = SCHEMA_TYPE_STRING)
        , name = "id"
        , description = SENHA_CONTROLLER_PARAMETER_ID_DESCRIPTION
        , example = SENHA_CONTROLLER_PARAMETER_ID_EXAMPLE)
public @interface ParameterSenhaId {
}
