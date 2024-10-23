package br.ufsm.redescomp.nutrigest.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ResponseErrorHandler {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Void> handle404NotFound() {
        return ResponseEntity.notFound().build();
    }

}