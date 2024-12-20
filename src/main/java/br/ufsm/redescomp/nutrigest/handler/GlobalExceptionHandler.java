package br.ufsm.redescomp.nutrigest.handler;

import br.ufsm.redescomp.nutrigest.dto.FieldErrorDto;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<List<FieldErrorDto>> handle400BadRequestError(Exception e) {
        List<FieldErrorDto> errors = null;

        if (e instanceof MethodArgumentNotValidException exception) {
            errors = exception.getBindingResult().getFieldErrors()
                    .stream()
                    .map(FieldErrorDto::new)
                    .toList();
        } else if (e.getCause() instanceof MismatchedInputException exception) {
            errors = List.of(new FieldErrorDto(
                    exception.getPath().getFirst().getFieldName(),
                    "É esperado um valor de tipo " + exception.getTargetType().getSimpleName()
            ));
        }

        return errors != null
                ? ResponseEntity.badRequest().body(errors)
                : ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Void> handleUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}