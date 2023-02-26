package br.tec.didiproject.queueserviceapi.exceptions;

public class BadRequestBodyException extends QueueServiceApiException {
    public BadRequestBodyException(String message) {
        super(message);
    }
}
