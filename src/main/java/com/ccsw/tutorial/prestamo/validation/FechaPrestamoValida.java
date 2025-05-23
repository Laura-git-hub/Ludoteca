package com.ccsw.tutorial.prestamo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaPrestamoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface FechaPrestamoValida {
    String message() default "La fecha de devolución no puede ser anterior a la fecha de préstamo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


