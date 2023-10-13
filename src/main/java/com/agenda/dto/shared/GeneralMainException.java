package com.agenda.dto.shared;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralMainException extends Exception {

    private final HttpStatus code;
    private final String message;

    private List<GeneralError> errors = new ArrayList<>();

    public GeneralMainException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public GeneralMainException(HttpStatus code, String message, GeneralError error) {
        this.code = code;
        this.message = message;

        addError(error);
    }

    public GeneralMainException(HttpStatus httpStatus, String message, List<GeneralError> errors) {
        this.code = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    private void addError(GeneralError error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}