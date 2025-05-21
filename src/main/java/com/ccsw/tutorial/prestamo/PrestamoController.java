package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar una lista de préstamos filtrados
     *
     * @param title  title del juego
     * @param idClient ID del cliente
     * @param date  Fecha para comprobar si hay préstamo activo
     * @return Lista de PrestamoDto
     */
    @Operation(summary = "Find", description = "Returns a filtered list of Prestamos")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PrestamoDto> find(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "idClient", required = false) Long idClient,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Prestamo> prestamos = prestamoService.find(title, idClient, date);

        return prestamos.stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList());
    }

    //Metodo para crear o actualizar
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Prestamo")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamoDto dto) {

        prestamoService.save(id, dto);
    }
}
