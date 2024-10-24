package br.ufsm.redescomp.nutrigest.handler;

import br.ufsm.redescomp.nutrigest.dto.FieldErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ResponseErrorHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handle404NotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorResponse>> handle400BadRequestError(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(f -> new FieldErrorResponse(f.getField(), Objects.requireNonNull(f.getDefaultMessage())))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

}