package br.tec.didiproject.queueserviceapi.utils.annotations.swagger;

import br.tec.didiproject.queueserviceapi.dtos.ApiErrorDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.ControllerAnnotationsV1.CONTROLLER_409_DESCRIPTION;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "409"
        , description = CONTROLLER_409_DESCRIPTION
        , content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
public @interface ApiResponse409 {
}
