package com.agenda.models;

import com.agenda.dto.agendas.AgendaInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "AgendaModel")
@Table(name = "agenda")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private String diasSemana;

    private Integer tempoConsulta;
    private String horarioInicio;

    private String horarioFim;

    private Boolean suprimirAgenda;

}
