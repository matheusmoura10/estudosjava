package com.agenda.dto.agendas;

import com.agenda.infra.helpers.DataHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class DiaHorarioDto {

    private LocalDate data;
    private Integer totalConsulta = 0;
    private String dataExtenso;
    private List<HorarioDto> horarios = new ArrayList<>();

    public DiaHorarioDto(LocalDate inicio) {
        this.data = inicio;

        this.dataExtenso = DataHelper.converterLocalDatePorExtenso(inicio);
    }

    public void addHorario(HorarioDto horario) {
        this.horarios.add(horario);
        this.totalConsulta++;
    }
}
