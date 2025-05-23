package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
    @Override
    @EntityGraph(attributePaths = { "game", "client", "author", "category" })
    List<Prestamo> findAll(Specification<Prestamo> spec);

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findAll(Pageable pageable);

    //VALIDACIONES

    //Las fechas no se pueden solapar
    @Query("SELECT p FROM Prestamo p WHERE p.game.id = :gameId AND " + "(:fechaInicio <= p.fechaDevolucion AND :fechaFin >= p.fechaPrestamo)")
    List<Prestamo> findPrestamosSolapados(@Param("gameId") Long gameId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    //Cliente NO puede tener más de 2 juegos prestador el mismo día
    @Query("SELECT p FROM Prestamo p WHERE p.client.id = :clientId AND " + "(:fechaInicio <= p.fechaDevolucion AND :fechaFin >= p.fechaPrestamo)")
    List<Prestamo> findPrestamosClienteSolapados(@Param("clientId") Long clientId, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

}

