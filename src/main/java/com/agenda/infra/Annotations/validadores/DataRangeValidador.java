package com.agenda.infra.Annotations.validadores;

import com.agenda.dto.agendas.DataRangeDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class DataRangeValidador implements ConstraintValidator<ValidaDataRange, DataRangeDto> {
    @Override
    public boolean isValid(DataRangeDto value, ConstraintValidatorContext context) {
        return value.getInicio().isBefore(value.getFim());
    }
}