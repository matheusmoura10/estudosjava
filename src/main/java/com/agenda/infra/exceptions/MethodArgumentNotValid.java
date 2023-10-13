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
public class MethodArgumentNotValid extends Exception {

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResponseEntity<GeneralMainException> handleIllegalArgumentException(org.springframework.web.bind.MethodArgumentNotValidException ex) {

        var errors = new ArrayList<GeneralError>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(new GeneralError(fieldError.getField(), fieldError.getDefaultMessage())));

        if (errors.isEmpty()) {
            ex.getBindingResult().getGlobalErrors().forEach(globalError -> errors.add(new GeneralError(globalError.getObjectName(), globalError.getDefaultMessage())));
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                new GeneralMainException(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Erro de validação",
                        errors
                )
        );

    }
}
