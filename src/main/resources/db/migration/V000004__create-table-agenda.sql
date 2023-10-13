create table agenda
(
    id              bigint      not null auto_increment,
    medico_id       bigint      not null,
    data_inicio     DATETIME    not null,
    data_fim        DATETIME    not null,
    dias_semana     varchar(13) not null,
    tempo_consulta  int         not null,
    horario_inicio  VARCHAR(8)  not null,
    horario_fim     VARCHAR(8)  not null,
    suprimir_agenda tinyint     not null,

    primary key (id),
    FOREIGN KEY (medico_id) REFERENCES medicos (id)
);
