package com.agenda.dto.medicos;

import com.agenda.dto.enderecos.EnderecoDto;
import com.agenda.enums.EspecialidadeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoInputDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Email(message = "O email é inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @NotBlank(message = "O CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull(message = "A especialidade é obrigatória")
        EspecialidadeEnum especialidade,

        @NotNull(message = "O endereço é obrigatório")
        @Valid
        EnderecoDto endereco
) {


}
