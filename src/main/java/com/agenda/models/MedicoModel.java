package com.agenda.models;

import com.agenda.dto.medicos.MedicoInputDto;
import com.agenda.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "MedicoModel")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private EspecialidadeEnum especialidade;
    @Embedded
    private EnderecoModel endereco;


    public MedicoModel(MedicoInputDto medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new EnderecoModel(medico.endereco());
    }

    public MedicoModel atualizar(MedicoInputDto medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new EnderecoModel(medico.endereco());

        return this;
    }

    public MedicoModel desativar() {
        this.ativo = false;
        return this;
    }

    public MedicoModel ativar() {
        this.ativo = true;
        return this;
    }
}
