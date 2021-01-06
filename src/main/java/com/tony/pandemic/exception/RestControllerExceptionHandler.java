package com.tony.pandemic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<StandardError> empty(EmptyException e) {
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(InvalidNegotiationException.class)
    public ResponseEntity<StandardError> invalidNegotiation(InvalidNegotiationException e) {
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
