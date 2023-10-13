package com.agenda.infra.exceptions;

import com.agenda.dto.shared.GeneralError;
import com.agenda.dto.shared.GeneralMainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class DataIntegrityViolationException extends Exception {

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResponseEntity<GeneralMainException> handleIllegalArgumentException(org.springframework.dao.DataIntegrityViolationException ex) {

        var errors = new ArrayList<GeneralError>();

        errors.add(new GeneralError("message", ex.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GeneralMainException(
                        HttpStatus.BAD_REQUEST,
                        "Houve um erro de integridade de dados",
                        new GeneralError("SQL",ex.getMessage())
                )
        );
    }
}
