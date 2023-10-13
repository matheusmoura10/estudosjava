package com.agenda.dto.medicos;

import com.agenda.models.MedicoModel;

public record MedicoOutputDto(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,

        Boolean ativo
        ) {

    public static MedicoOutputDto criar(MedicoModel medico) {
        return new MedicoOutputDto(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getAtivo()
        );
    }
}
