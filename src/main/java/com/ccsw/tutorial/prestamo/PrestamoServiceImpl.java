package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.author.AuthorService;
import com.ccsw.tutorial.category.CategoryService;
import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    //Listado paginado
    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {
        return this.prestamoRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public List<Prestamo> find(String title, Long idClient, LocalDate fechaPrestamo) {
        Specification<Prestamo> spec = Specification.where(null);

        if (title != null) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("game.title", ":", title)));
        }

        if (idClient != null) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("client.id", ":", idClient)));
        }

        if (fechaPrestamo != null) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("fechaPrestamo", ":", fechaPrestamo)));
        }

        return prestamoRepository.findAll(spec);
    }


    /*@Override
    public List<Prestamo> find(String title, Long idClient, LocalDate fechaPrestamo) {
        PrestamoSpecification titleSpec = new PrestamoSpecification(new SearchCriteria("title", ":", title));
        PrestamoSpecification clientSpec = new PrestamoSpecification(new SearchCriteria("client.id", ":", idClient));
        PrestamoSpecification fechaPrestamoSpec = new PrestamoSpecification(new SearchCriteria("fechaPrestamo", ":", fechaPrestamo));

        Specification<Prestamo> spec = Specification.where(titleSpec).and(clientSpec).and(fechaPrestamoSpec);
        return prestamoRepository.findAll(spec);
    }*/

    /*@Override
    public void save(Long id, PrestamoDto dto) {
        Prestamo prestamo;

        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "client", "author", "category");

        prestamo.setGame(gameService.get(dto.getGame().getId()));
        prestamo.setClient(clientService.get(dto.getClient().getId()));
        prestamo.setAuthor(authorService.get(dto.getAuthor().getId()));
        prestamo.setCategory(categoryService.get(dto.getCategory().getId()));

        this.prestamoRepository.save(prestamo);

    }*/

    @Override
    public void delete(Long id) throws Exception {
        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }

    @Override
    public Page<Prestamo> findPage(String gameTitle, String clientName, LocalDate date, PrestamoSearchDto dto) {
        return this.prestamoRepository.findAll(dto.getPageable().getPageable());
    }

    //APLICAR VALIDACION
    @Override
    public void save(Long id, PrestamoDto dto) {
        // Validación de solapamiento de juego
        List<Prestamo> solapados = prestamoRepository.findPrestamosSolapados(dto.getGame().getId(), dto.getFechaPrestamo(), dto.getFechaDevolucion());

        boolean conflicto = solapados.stream().anyMatch(p -> !p.getClient().getId().equals(dto.getClient().getId()) || (id == null || !p.getId().equals(id)));

        if (conflicto) {
            throw new IllegalArgumentException("El juego ya está prestado a otro cliente en ese rango de fechas.");
        }

        // Validación de máximo 2 préstamos por cliente en el mismo rango
        List<Prestamo> prestamosCliente = prestamoRepository.findPrestamosClienteSolapados(dto.getClient().getId(), dto.getFechaPrestamo(), dto.getFechaDevolucion());

        long totalPrestamos = prestamosCliente.stream().filter(p -> id == null || !p.getId().equals(id)) // excluir el préstamo actual si es edición
                .count();

        if (totalPrestamos >= 2) {
            throw new IllegalArgumentException("El cliente ya tiene 2 préstamos activos en ese rango de fechas.");
        }

        // Crear o actualizar el préstamo
        Prestamo prestamo;

        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "client", "author", "category");

        prestamo.setGame(gameService.get(dto.getGame().getId()));
        prestamo.setClient(clientService.get(dto.getClient().getId()));
        prestamo.setAuthor(authorService.get(dto.getAuthor().getId()));
        prestamo.setCategory(categoryService.get(dto.getCategory().getId()));

        this.prestamoRepository.save(prestamo);
    }
}



