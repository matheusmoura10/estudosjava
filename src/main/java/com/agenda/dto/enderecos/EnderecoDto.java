package com.agenda.dto.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(
        @NotBlank(message = "O Logradouro é obrigatório")
        String logradouro,
        String numero,
        String complemento,
        @NotBlank(message = "O Bairro é obrigatório")
        String bairro,
        @NotBlank(message = "A Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "O UF é obrigatório")
        String uf,
        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "[0-9]{5}[-][0-9]{3}", message = "O CEP é inválido")
        String cep
) {

}
