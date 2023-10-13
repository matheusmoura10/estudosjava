package com.agenda.dto.agendas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class HorarioRangeDto {

    @NotNull(message = "O campo horario inicio é obrigatório")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "O horário deve estar no formato HH:mm")
    String inicio;

    @NotNull(message = "O campo horario fim é obrigatório")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "O horário deve estar no formato HH:mm")
    String fim;


    public LocalTime getInicio() {
        return LocalTime.parse(inicio);
    }

    public LocalTime getFim() {
        return LocalTime.parse(fim);
    }

}
