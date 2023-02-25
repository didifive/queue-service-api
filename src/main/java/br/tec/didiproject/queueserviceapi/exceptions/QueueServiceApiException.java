package br.tec.didiproject.queueserviceapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueueServiceApiException extends RuntimeException{
    private final String message;
}