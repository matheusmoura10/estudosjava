package com.agenda.infra.helpers;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DataHelper {

    public static String converterLocalDatePorExtenso(LocalDate data) {
        return data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")) . concat(", ")
                .concat(String.valueOf(data.getDayOfMonth())).concat(" de ")
                .concat(data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))).concat(" de ")
                .concat(String.valueOf(data.getYear()));
    }
}
