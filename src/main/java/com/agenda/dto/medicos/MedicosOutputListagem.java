package com.agenda.dto.medicos;

import com.agenda.enums.EspecialidadeEnum;
import com.agenda.models.MedicoModel;

public record MedicosOutputListagem(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        EspecialidadeEnum especialidade,

        Boolean ativo
) {

    public static MedicosOutputListagem criar(MedicoModel medico) {
        return new MedicosOutputListagem(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getAtivo()
        );
    }
}
