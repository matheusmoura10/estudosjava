package com.agenda.dto.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String message = "Sucesso";
    private Object data = null;
}
