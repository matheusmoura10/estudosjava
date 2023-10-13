package com.agenda.infra.Annotations.validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataRangeValidador.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaDataRange {

    String message() default "A data de início deve ser anterior a data de fim";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}