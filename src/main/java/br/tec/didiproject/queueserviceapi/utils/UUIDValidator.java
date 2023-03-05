package br.tec.didiproject.queueserviceapi.utils;

import br.tec.didiproject.queueserviceapi.exceptions.BadRequestBodyException;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.UUID_BAD_REQUEST;


public class UUIDValidator {

    public static void validateUUIDPattern(String uuidString) {
        String regex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";
        if (!uuidString.matches(regex))
            throw new BadRequestBodyException(
                    UUID_BAD_REQUEST.params(uuidString).getMessage()
            );
    }

    private UUIDValidator() {
        throw new IllegalAccessError("Utility Class");
    }
}