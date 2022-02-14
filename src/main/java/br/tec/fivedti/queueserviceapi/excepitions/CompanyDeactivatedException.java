package br.tec.fivedti.queueserviceapi.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyDeactivatedException extends Exception {
    public CompanyDeactivatedException(UUID id) {
        super(String.format("Company with ID %s is deactivated!", id));
    }
}