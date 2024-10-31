package br.ufsm.redescomp.nutrigest.handler;

import br.ufsm.redescomp.nutrigest.dto.FieldErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handle404NotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<List<FieldErrorResponse>> handle400BadRequestError(Exception e) {
        List<FieldErrorResponse> errors = null;

        if (e instanceof MethodArgumentNotValidException exception) {
            errors = exception.getBindingResult().getFieldErrors()
                    .stream()
                    .map(f -> new FieldErrorResponse(f.getField(), Objects.requireNonNull(f.getDefaultMessage())))
                    .toList();
        } else if (e.getCause() instanceof MismatchedInputException exception) {
            errors = List.of(new FieldErrorResponse(
                    exception.getPath().getFirst().getFieldName(),
                    "É esperado um valor de tipo " + exception.getTargetType().getSimpleName()
            ));
        }

        return errors != null
                ? ResponseEntity.badRequest().body(errors)
                : ResponseEntity.badRequest().build();
    }

}