package com.agenda.infra.Annotations.validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HoraRangeValidador.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaHoraRange {
    String message() default "O horário de início deve ser anterior ao horário de fim";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
