package com.example.carros.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public ResponseEntity errorNotFound(Exception ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado"));
    }
}

class Error {
    public String error;

    public Error(String error) {
        this.error = error;
    }
}