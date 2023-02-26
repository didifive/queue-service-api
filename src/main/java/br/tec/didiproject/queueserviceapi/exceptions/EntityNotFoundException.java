package br.tec.didiproject.queueserviceapi.exceptions;

public class EntityNotFoundException extends QueueServiceApiException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
