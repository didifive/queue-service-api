package br.tec.fivedti.queueserviceapi.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException(Long id) {
        super(String.format("Company with ID %s not found!", id));
    }
}