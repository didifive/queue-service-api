package br.tec.didiproject.queueserviceapi.exceptions;

public class DataIntegrityViolationException extends QueueServiceApiException {
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
