package br.tec.fivedti.queueserviceapi.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QueueNotFoundException extends Exception {
    public QueueNotFoundException(UUID id) {
        super(String.format("Queue with ID %s not found!", id));
    }
}