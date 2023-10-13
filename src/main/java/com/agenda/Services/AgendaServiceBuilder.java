package com.agenda.Services;

import com.agenda.Repositories.Interface.AgendaRepository;
import com.agenda.dto.agendas.ConstruirAgendaDto;
import com.agenda.dto.agendas.DiaHorarioDto;
import com.agenda.dto.agendas.DiasDisponiveisDto;
import com.agenda.dto.agendas.HorarioDto;
import com.agenda.enums.AgendaEnum;
import com.agenda.mappers.AgendaMapper;
import com.agenda.models.AgendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@Service
public class AgendaServiceBuilder {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaMapper agendaMapper;

    @Autowired
    private MedicoService medicoService;

    public DiasDisponiveisDto obterAgenda(Long id) {

        AgendaModel agenda = agendaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(AgendaEnum.AGENDA_NAO_ENCONTRADA.getMensagem()));

        ConstruirAgendaDto dto = agendaMapper.modelToConstruirAgendaDto(agenda);

        return getDiasDisponiveis(dto);
    }

    private DiasDisponiveisDto getDiasDisponiveis(ConstruirAgendaDto dto) {

        DiasDisponiveisDto diasDisponiveis = new DiasDisponiveisDto(
                dto.getId(),
                medicoService.show(
                        dto.getMedicoId()
                )
        );

        LocalDate diaAtual = dto.getData().getInicio();
        LocalDate diaFinal = dto.getData().getFim().plusDays(1);

        while (diaAtual.isBefore(diaFinal)) {
            if (diaAutorizado(dto, diaAtual)) {
                DiaHorarioDto diaHorario = criarDiaHorario(diaAtual);
                populateHorarios(diaHorario, dto);
                diasDisponiveis.adicionarDia(diaHorario);

                diasDisponiveis.incrementarContador(diaHorario.getTotalConsulta());
            }
            diaAtual = diaAtual.plusDays(1);
        }

        return diasDisponiveis;
    }

    private void populateHorarios(DiaHorarioDto diaHorario, ConstruirAgendaDto dto) {

        LocalDateTime inicio = LocalDateTime.of(diaHorario.getData(), dto.getHorario().getInicio());
        LocalDateTime fim = LocalDateTime.of(diaHorario.getData(), dto.getHorario().getFim());

        while (inicio.isBefore(fim)) {
            int tempoConsulta = dto.getTempoConsulta();
            if (deveAjustarAgenda(inicio, fim, dto)) {
                tempoConsulta = ajustarAgenda(inicio, fim, dto);
            }

            HorarioDto horario = new HorarioDto(inicio, inicio.plusMinutes(tempoConsulta), tempoConsulta);
            diaHorario.addHorario(horario);
            inicio = inicio.plusMinutes(tempoConsulta);
        }
    }

    private boolean diaAutorizado(ConstruirAgendaDto dto, LocalDate currentDate) {
        int diaDaSemana = currentDate.getDayOfWeek().getValue();
        return dto.getDiasLiberados().contains(Integer.toString(diaDaSemana));
    }

    private DiaHorarioDto criarDiaHorario(LocalDate data) {
        return new DiaHorarioDto(data);
    }

    private boolean deveAjustarAgenda(LocalDateTime inicio, LocalDateTime fim, ConstruirAgendaDto dto) {
        return inicio.plusMinutes(dto.getTempoConsulta()).isAfter(fim) && dto.deveSuprimirAgenda();
    }

    private int ajustarAgenda(LocalDateTime startTime, LocalDateTime endTime, ConstruirAgendaDto dto) {
        LocalDateTime lastTime = startTime.plusMinutes(dto.getTempoConsulta());
        int minutesDifference = lastTime.getMinute() - endTime.getMinute();
        dto.setTempoConsulta(dto.getTempoConsulta() - minutesDifference);
        return dto.getTempoConsulta();

    }
}
