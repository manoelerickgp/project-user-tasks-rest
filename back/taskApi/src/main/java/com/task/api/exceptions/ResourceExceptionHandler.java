package com.task.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handlerNotFoundException(NotFoundException e, WebRequest request) {
        ResponseError error = new ResponseError();
        error.setMessage(e.getMessage());
        error.setDetails(request.getDescription(false));
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
