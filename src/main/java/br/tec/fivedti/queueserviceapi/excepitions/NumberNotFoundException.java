package br.tec.fivedti.queueserviceapi.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NumberNotFoundException extends Exception {
    public NumberNotFoundException(UUID id) {
        super(String.format("Number with ID %s not found!", id));
    }
}