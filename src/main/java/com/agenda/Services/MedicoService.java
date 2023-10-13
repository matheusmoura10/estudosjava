package com.agenda.Services;

import com.agenda.Repositories.Interface.MedicoRepository;
import com.agenda.dto.medicos.MedicoInputDto;
import com.agenda.dto.medicos.MedicoOutputDto;
import com.agenda.dto.medicos.MedicosOutputListagem;
import com.agenda.enums.MensagensEnum;
import com.agenda.models.MedicoModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public Page<MedicosOutputListagem> listarPaginadoAtivos(Pageable paginacao, String findBy, String findByValue) {
        return medicoRepository.findAllByFieldValueLikePaginate(findBy, findByValue, paginacao, false).map(MedicosOutputListagem::criar);
    }

    @Transactional
    public MedicoOutputDto store(@RequestBody @Valid MedicoInputDto medico) {
        MedicoModel response = medicoRepository.save(new MedicoModel(medico));

        return MedicoOutputDto.criar(response);
    }


    public MedicoOutputDto show(Long id) {

        MedicoModel medico = obterMedicoPorId(id);

        return MedicoOutputDto.criar(medico);
    }

    public MedicoOutputDto update(Long id, MedicoInputDto medico) {

        MedicoModel medicoModel = obterMedicoPorId(id);

        MedicoModel response = medicoRepository.save(medicoModel.atualizar(medico));

        return MedicoOutputDto.criar(response);
    }

    private MedicoModel obterMedicoPorId(Long id) {
        return medicoRepository.findByIdAndAtivoTrue(id).map(MedicoModel.class::cast).orElseThrow(
                () -> new NoSuchElementException(MensagensEnum.MEDICO_NAO_ENCONTRADO.getMensagem())
        );
    }

    public void delete(Long id) {
        MedicoModel medico = this.obterMedicoPorId(id).desativar();

        medicoRepository.save(medico.desativar());
    }
}
