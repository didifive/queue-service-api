package br.tec.didiproject.queueserviceapi.utils.annotations.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_INTEGER;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiTypes.SCHEMA_TYPE_STRING;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY
        , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
        , name = "size"
        , description = CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
        , example = CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
@Parameter(in = ParameterIn.QUERY
        , schema = @Schema(type = SCHEMA_TYPE_INTEGER)
        , name = "page"
        , description = CONTROLLER_FIND_ALL_PARAMETER_PAGE
        , example = CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
@Parameter(in = ParameterIn.QUERY
        , schema = @Schema(type = SCHEMA_TYPE_STRING)
        , name = "sort"
        , description = CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
        , example = CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
@Parameter(name = "pageable", hidden = true)
public @interface PageParams {
}