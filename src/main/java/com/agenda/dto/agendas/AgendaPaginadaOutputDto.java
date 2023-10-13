package com.agenda.dto.agendas;

import com.agenda.Repositories.Interface.AgendaRepository;
import com.agenda.dto.medicos.MedicoOutputDto;
import com.agenda.models.AgendaModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AgendaPaginadaOutputDto {

    private Long id;
    private MedicoOutputDto medico;
    private DataRangeDto data;

    private HorarioRangeDto horario;

    private String diasSemana;

    private Integer tempoConsulta;

    private Boolean suprimirAgenda;

    public static AgendaPaginadaOutputDto criar(AgendaModel agendaModel) {
        return new AgendaPaginadaOutputDto(
                agendaModel.getId(),
                MedicoOutputDto.criar(agendaModel.getMedico()),
                new DataRangeDto(agendaModel.getDataInicio(), agendaModel.getDataFim()),
                new HorarioRangeDto(agendaModel.getHorarioInicio(), agendaModel.getHorarioFim()),
                agendaModel.getDiasSemana(),
                agendaModel.getTempoConsulta(),
                agendaModel.getSuprimirAgenda()
        );
    }
}
