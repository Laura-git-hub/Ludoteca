package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoService {

    /**
     * Recupera los préstamos filtrando opcionalmente por juego, cliente y fecha
     *
     * @param idGame   ID del juego
     * @param idClient ID del cliente
     * @param date     Fecha para comprobar si hay préstamo activo
     * @return Lista de PrestamoDto
     */
    List<PrestamoDto> find(Long idGame, Long idClient, LocalDate date);

    List<Prestamo> findEntities(Long idGame, Long idClient, LocalDate date);
}
