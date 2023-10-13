package com.agenda.dto.agendas;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class DataRangeDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fim;

}
