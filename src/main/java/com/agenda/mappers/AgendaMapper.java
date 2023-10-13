package com.agenda.mappers;

import com.agenda.dto.agendas.AgendaInputDto;
import com.agenda.dto.agendas.AgendaOutputDto;
import com.agenda.dto.agendas.ConstruirAgendaDto;
import com.agenda.models.AgendaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgendaMapper {
    AgendaMapper INSTANCE = Mappers.getMapper(AgendaMapper.class);

    @Mapping(target = "medico.id", source = "medicoId")
    @Mapping(target = "dataInicio", source = "data.inicio")
    @Mapping(target = "dataFim", source = "data.fim")
    @Mapping(target = "horarioInicio", source = "horario.inicio")
    @Mapping(target = "horarioFim", source = "horario.fim")
    AgendaModel agendaInputDtoToModel(AgendaInputDto agendaInputDto);

    @Mapping(target = "medicoId", source = "medico.id")
    @Mapping(target = "data.inicio", source = "dataInicio")
    @Mapping(target = "data.fim", source = "dataFim")
    @Mapping(target = "horario.inicio", source = "horarioInicio")
    @Mapping(target = "horario.fim", source = "horarioFim")
    AgendaOutputDto agendaModelToOutputDto(AgendaModel agendaModel);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "medicoId", source = "medico.id")
    @Mapping(target = "data.inicio", source = "dataInicio")
    @Mapping(target = "data.fim", source = "dataFim")
    @Mapping(target = "horario.inicio", source = "horarioInicio")
    @Mapping(target = "horario.fim", source = "horarioFim")
    @Mapping(target = "diasLiberados", source = "diasSemana")
    ConstruirAgendaDto modelToConstruirAgendaDto(AgendaModel agenda);
}

