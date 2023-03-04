package br.tec.didiproject.queueserviceapi.utils.valid_uuid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
public @interface ValidUUID {

    String message() default "{ValidUUID.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
