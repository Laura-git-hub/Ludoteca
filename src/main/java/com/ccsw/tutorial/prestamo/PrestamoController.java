package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo") // corregido: antes decía "/pretamo"
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final ModelMapper mapper;

    // Inyección por constructor (más limpia y testable)
    public PrestamoController(PrestamoService prestamoService, ModelMapper mapper) {
        this.prestamoService = prestamoService;
        this.mapper = mapper;
    }

    /**
     * Método para recuperar una lista de préstamos filtrados
     *
     * @param idGame   ID del juego
     * @param idClient ID del cliente
     * @param date     Fecha para comprobar si hay préstamo activo
     * @return Lista de PrestamoDto
     */
    @Operation(summary = "Find", description = "Returns a filtered list of Prestamos")
    @GetMapping("")
    public List<PrestamoDto> find(@RequestParam(value = "idGame", required = false) Long idGame, @RequestParam(value = "idClient", required = false) Long idClient,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Prestamo> prestamos = prestamoService.findEntities(idGame, idClient, date);

        return prestamos.stream().map(prestamo -> mapper.map(prestamo, PrestamoDto.class)).collect(Collectors.toList());
    }
}
