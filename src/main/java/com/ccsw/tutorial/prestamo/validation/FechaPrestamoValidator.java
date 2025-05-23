package com.ccsw.tutorial.prestamo.validation;

import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ChronoUnit;

public class FechaPrestamoValidator implements ConstraintValidator<FechaPrestamoValida, PrestamoDto> {

    @Override
    public boolean isValid(PrestamoDto dto, ConstraintValidatorContext context) {
        if (dto.getFechaPrestamo() == null || dto.getFechaDevolucion() == null) {
            return true; // O false si quieres forzar que ambas fechas estén presentes
        }

        boolean isValid = true;

        if (dto.getFechaDevolucion().isBefore(dto.getFechaPrestamo())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La fecha de devolución no puede ser anterior a la fecha de préstamo").addPropertyNode("fechaDevolucion").addConstraintViolation();
            isValid = false;
        }

        long dias = ChronoUnit.DAYS.between(dto.getFechaPrestamo(), dto.getFechaDevolucion());
        if (dias > 14) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El período de préstamo no puede ser mayor a 14 días").addPropertyNode("fechaDevolucion").addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
