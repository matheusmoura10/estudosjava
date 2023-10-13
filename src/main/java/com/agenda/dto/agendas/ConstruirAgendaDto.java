package com.agenda.dto.agendas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ConstruirAgendaDto {

    private Long id;

    private Long medicoId;

    private DataRangeDto data;

    private HorarioRangeDto horario;

    String diasLiberados;

    Integer tempoConsulta;

    Boolean suprimirAgenda;

    public boolean deveSuprimirAgenda() {
        return this.getSuprimirAgenda();
    }

    public List<String> getDiasLiberados() {
        return List.of(this.diasLiberados.split(","));
    }
}
