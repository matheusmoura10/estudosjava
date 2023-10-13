package com.agenda.infra.exceptions;

import com.agenda.dto.shared.GeneralMainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoSuchElement {

    @ExceptionHandler(java.util.NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<GeneralMainException> handleNoSuchElementException(java.util.NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new GeneralMainException(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage() != null ? ex.getMessage() : "Não encontrado"
                )
        );
    }
}
