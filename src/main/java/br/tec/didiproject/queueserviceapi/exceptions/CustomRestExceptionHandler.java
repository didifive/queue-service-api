package br.tec.didiproject.queueserviceapi.exceptions;

import br.tec.didiproject.queueserviceapi.dtos.ApiErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.*;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( { QueueServiceApiException.class } )
    public ResponseEntity<ApiErrorDTO> handleQueueServiceApiException(QueueServiceApiException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError(GENERIC_EXCEPTION.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler( { EntityNotFoundException.class } )
    public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(GENERIC_NOT_FOUND.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler( { DataIntegrityViolationException.class } )
    public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError(GENERIC_DATA_INTEGRITY_VIOLATION.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler( { BadRequestBodyException.class } )
    public ResponseEntity<ApiErrorDTO> handleBadRequestBodyException(BadRequestBodyException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(GENERIC_BAD_REQUEST.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler( { TokenRefreshException.class } )
    public ResponseEntity<ApiErrorDTO> handleTokenRefreshException(TokenRefreshException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.FORBIDDEN.value());
        err.setError(GENERIC_FORBIDDEN.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler( { InvalidCredetialsException.class } )
    public ResponseEntity<ApiErrorDTO> handleInvalidCredetialsException(InvalidCredetialsException e, HttpServletRequest request){
        ApiErrorDTO err = new ApiErrorDTO();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setError(GENERIC_FORBIDDEN.getMessage());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

}
