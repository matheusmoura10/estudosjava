package com.agenda.dto.agendas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class HorarioDto {

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Integer tempoConsulta;

}
