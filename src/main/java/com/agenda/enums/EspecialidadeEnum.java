package com.agenda.enums;

public enum EspecialidadeEnum {
    CARDIOLOGIA("Cardiologia"),
    CLINICO_GERAL("Clinico Geral"),
    DERMATOLOGIA("Dermatologia"),
    ENDOCRINOLOGIA("Endocrinologia"),

    GASTROENTEROLOGIA("Gastroenterologia");

    private final String especialidade;

    EspecialidadeEnum(String especialidade) {
        this.especialidade = especialidade;
    }
}
