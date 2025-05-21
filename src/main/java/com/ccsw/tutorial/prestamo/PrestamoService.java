package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoService {

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**


     /**
     * Recupera los préstamos filtrando opcionalmente por juego, cliente y fecha
     */

    List<Prestamo> find(String title, Long idCliente, LocalDate fechaPrestamo);

    void save(Long id, PrestamoDto dto);
}
