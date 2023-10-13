package com.agenda.Services;

import com.agenda.Repositories.Interface.AgendaRepository;
import com.agenda.Repositories.Interface.MedicoRepository;
import com.agenda.dto.agendas.AgendaInputDto;
import com.agenda.dto.agendas.AgendaOutputDto;
import com.agenda.dto.agendas.AgendaPaginadaOutputDto;
import com.agenda.enums.AgendaEnum;
import com.agenda.mappers.AgendaMapper;
import com.agenda.models.AgendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AgendaService {
    @Autowired
    private AgendaServiceBuilder agendaServiceBuilder;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendaMapper agendaMapper;


    public AgendaOutputDto store(AgendaInputDto agendaInputDto) {

        AgendaModel agendaModel = agendaMapper.agendaInputDtoToModel(agendaInputDto);
        agendaModel = agendaRepository.saveAndFlush(agendaModel);
        agendaRepository.refresh(agendaModel);


        return agendaMapper.agendaModelToOutputDto(agendaModel);
    }

    public AgendaOutputDto show(Long id) {
        AgendaModel agendaModel = obterAgenda(id);

        return agendaMapper.agendaModelToOutputDto(agendaModel);
    }

    private AgendaModel obterAgenda(Long id) {
        return agendaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(AgendaEnum.AGENDA_NAO_ENCONTRADA.getMensagem()));
    }

    public AgendaOutputDto update(Long id, AgendaInputDto agenda) {
        AgendaModel agendaModel = obterAgenda(id);

        AgendaModel agendaModelAtualizada = agendaMapper.agendaInputDtoToModel(agenda);
        agendaModelAtualizada.setId(agendaModel.getId());

        agendaModelAtualizada = agendaRepository.saveAndFlush(agendaModelAtualizada);
        agendaRepository.refresh(agendaModelAtualizada);

        return agendaMapper.agendaModelToOutputDto(agendaModelAtualizada);
    }

    public Boolean delete(Long id) {
        AgendaModel agendaModel = obterAgenda(id);

        agendaRepository.delete(agendaModel);

        return true;
    }

    public Page<AgendaPaginadaOutputDto> index(Pageable paginacao, String findBy, String findByValue) {
        return agendaRepository.findAllByFieldValueLikePaginate(findBy, findByValue, paginacao);
    }
}
