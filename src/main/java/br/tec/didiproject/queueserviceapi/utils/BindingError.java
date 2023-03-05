package br.tec.didiproject.queueserviceapi.utils;

import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class BindingError {

    public static void checkBindingResultError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBodyException(
                    bindingResult.getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining("||")));
        }
    }

    private BindingError() {
        throw new IllegalAccessError("Utility Class");
    }

}
