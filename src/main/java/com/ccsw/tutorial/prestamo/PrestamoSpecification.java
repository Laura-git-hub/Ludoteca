package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PrestamoSpecification {

    public static Specification<Prestamo> hasGame(Long idGame) {
        return (root, query, cb) -> idGame == null ? null : cb.equal(root.get("game").get("id"), idGame);
    }

    public static Specification<Prestamo> hasClient(Long idClient) {
        return (root, query, cb) -> idClient == null ? null : cb.equal(root.get("client").get("id"), idClient);
    }

    public static Specification<Prestamo> isActiveOnDate(LocalDate date) {
        return (root, query, cb) -> date == null ? null : cb.and(cb.lessThanOrEqualTo(root.get("fechaPrestamo"), date), cb.greaterThanOrEqualTo(root.get("fechaDevolucion"), date));
    }
}
