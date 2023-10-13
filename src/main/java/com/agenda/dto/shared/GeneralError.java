package com.agenda.dto.shared;

import lombok.Data;

@Data
public class GeneralError {
    private String field;
    private String message;

    public GeneralError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public GeneralError(String message) {
        this.message = message;
    }

}
