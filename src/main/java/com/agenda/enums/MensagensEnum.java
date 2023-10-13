package com.agenda.enums;

import lombok.Getter;

@Getter
public enum MensagensEnum {

    MENSAGEM_ERRO_GENERICO("Ocorreu um erro inesperado, tente novamente mais tarde."),

    MENSAGEM_ERRO_VALIDACAO("Ocorreu um erro de validação, verifique os campos e tente novamente."),

    MEDICO_NAO_ENCONTRADO("Médico não encontrado."),

    NAO_FOI_POSSIVEL_DELETAR_O_REGISTRO("Não foi possível deletar o registro.");

    private final String mensagem;

    MensagensEnum(String mensagem) {
        this.mensagem = mensagem;
    }

}
