package com.agenda.dto.shared;

import org.springframework.http.HttpStatus;

public record GeneralResponse(
        HttpStatus code,
        String message

) {
}
