package com.agenda.dto.agendas;

import lombok.AllArgsConstructor;
public class AgendaOutputDto extends AgendaInputDto {

    private Long id;
    public AgendaOutputDto(Long id, Long medicoId, DataRangeDto data, HorarioRangeDto horario, String diasSemana, Integer tempoConsulta, Boolean suprimirAgenda) {
        super(medicoId, data, diasSemana, tempoConsulta, horario, suprimirAgenda);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
