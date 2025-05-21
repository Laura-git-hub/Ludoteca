package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoService {

    /**
     * Recupera los préstamos filtrando opcionalmente por juego, cliente y fecha
     */

    List<Prestamo> find(String title, Long idCliente, LocalDate fechaPrestamo);

    void save(Long id, PrestamoDto dto);
}
