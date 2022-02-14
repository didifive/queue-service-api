package br.tec.fivedti.queueserviceapi.excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QueueDeactivatedException extends Exception {
    public QueueDeactivatedException(UUID id) {
        super(String.format("Queue with ID %s is deactivated!", id));
    }
}