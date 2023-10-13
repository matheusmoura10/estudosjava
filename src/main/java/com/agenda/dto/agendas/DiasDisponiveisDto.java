package com.agenda.dto.agendas;

import com.agenda.dto.medicos.MedicoOutputDto;
import com.agenda.models.MedicoModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class DiasDisponiveisDto {

    private Long id;
    private MedicoOutputDto medico;
    private Integer totalDias = 0;
    private Integer totalConsulta = 0;
    private List<DiaHorarioDto> dias = new ArrayList<>();


    public DiasDisponiveisDto(Long id, MedicoOutputDto medicoOutputDto) {
        this.id = id;
        this.medico = medicoOutputDto;
    }

    public void adicionarDia(DiaHorarioDto horario) {
        this.dias.add(horario);
        this.totalDias++;
    }
    public void incrementarContador(int totalConsulta) {
        this.totalConsulta += totalConsulta;
    }
}
