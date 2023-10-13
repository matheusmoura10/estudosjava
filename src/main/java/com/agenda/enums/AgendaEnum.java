package com.agenda.enums;

public enum AgendaEnum {

    AGENDA_NAO_ENCONTRADA("Agenda não encontrada"),
    AGENDA_DELETADA_COM_SUCESSO("Agenda deletada com sucesso"),
    AGENDA_ATUALIZADA_COM_SUCESSO("Agenda atualizada com sucesso"),
    AGENDA_SALVA_COM_SUCESSO("Agenda salva com sucesso"),
    AGENDA_OBITIDA_COM_SUCESSO("Agenda obtida com sucesso");

    private String mensagem;

    AgendaEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
