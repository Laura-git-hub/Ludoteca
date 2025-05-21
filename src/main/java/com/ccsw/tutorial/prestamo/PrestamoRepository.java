package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
    @Override
    @EntityGraph(attributePaths = { "game", "client", "author", "category" })
    List<Prestamo> findAll(Specification<Prestamo> spec);

}
