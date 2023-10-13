package com.agenda.dto.agendas;

import com.agenda.infra.Annotations.validadores.ValidaDataRange;
import com.agenda.infra.Annotations.validadores.ValidaHoraRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AgendaInputDto {

    @NotNull(message = "O campo medicoId é obrigatório")
    Long medicoId;
    @ValidaDataRange
    @Valid
    DataRangeDto data;

    @NotBlank(message = "O campo horario é obrigatório")
    String diasSemana;

    @NotNull(message = "O campo tempo consulta é obrigatório")
    @Min(value = 1, message = "O tempo de consulta deve ser maior que 0")
    @Max(value = 60 * 24, message = "O tempo de consulta deve ser menor que 1440")
    Integer tempoConsulta;

    @NotNull(message = "O campo horario é obrigatório")
    @ValidaHoraRange()
    @Valid
    HorarioRangeDto horario;

    @NotNull(message = "O campo corteAgenda é obrigatório")
    Boolean suprimirAgenda;
}