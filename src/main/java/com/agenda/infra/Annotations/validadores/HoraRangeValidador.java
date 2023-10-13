package com.agenda.infra.Annotations.validadores;

import com.agenda.dto.agendas.HorarioRangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;

public class HoraRangeValidador implements ConstraintValidator<ValidaHoraRange, HorarioRangeDto> {
    @Override
    public boolean isValid(HorarioRangeDto value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        String inicio = value.getInicio().toString();
        String fim = value.getFim().toString();

        //regex para validar o formato HH:mm
        var regex = "^([0-1]?[\\d]|2[0-3]):[0-5][\\d]$";

        if (!inicio.matches(regex) || !fim.matches(regex)) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            //is hour
            if (Integer.parseInt(inicio.substring(0, 2)) > 23) {
                return false;
            }

            //is hour
            if (Integer.parseInt(fim.substring(0, 2)) > 23) {
                return false;
            }

            return formatter.parse(inicio).before(formatter.parse(fim));
        } catch (Exception e) {
            return false;
        }
    }
}