package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class PrestamoIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;
    private static final String NOT_EXISTS_TITLE = "NotExists";
    private static final String EXISTS_TITLE = "Aventureros al tren";
    private static final String NEW_TITLE = "Squid Game";
    private static final Long NOT_EXISTS_CLIENT = 0L;
    private static final Long EXISTS_CLIENT = 3L;

    private static final String TITLE_PARAM = "title";
    private static final String CLIENT_ID_PARAM = "idClient";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<PrestamoDto>> responseType = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH).queryParam(TITLE_PARAM, "{" + TITLE_PARAM + "}").queryParam(CLIENT_ID_PARAM, "{" + CLIENT_ID_PARAM + "}").encode().toUriString();
    }

    //Buscar un titulo y un cliente que exsitan

    //Buscar un título que no exista

    //Buscar un cliente que no exista

    //Buscar un titulo y un cliente que no existan

    //Crear un prestamo nuevo

    //Modificar un prestamo que exista

    //Modificar un prestamo que no exista

    //Buscar un prestamo sin filtros

    @Test
    public void findWithoutFiltersShouldReturnAllPrestamosInDB() {

        int PRESTAMOS_WITH_FILTER = 5;

        Map<String, Object> params = new HashMap<>();
        params.put(TITLE_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        // Mostrar los datos de forma clara
        System.out.println("Lista de préstamos devueltos:");
        for (PrestamoDto prestamo : response.getBody()) {
            System.out.println("ID: " + prestamo.getId());
            System.out.println("Fecha de préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha de devolución: " + prestamo.getFechaDevolucion());
        }
    }

    //Buscar un título que exista
    @Test
    public void findExistsTitleShouldReturnPrestamos() {

        int PRESTAMOS_WITH_FILTER = 1;

        Map<String, Object> params = new HashMap<>();
        params.put(TITLE_PARAM, EXISTS_TITLE);
        params.put(CLIENT_ID_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        if (response.getBody() != null) {
            for (PrestamoDto prestamo : response.getBody()) {
                System.out.println("Prestamo ID: " + prestamo.getId());

            }
        }
    }
    // Buscar un cliente que exista

    @Test
    public void findExistsClientShouldReturnPrestamos() {

        int PRESTAMOS_WITH_FILTER = 1; // Número esperado de préstamos para el cliente

        Map<String, Object> params = new HashMap<>();
        params.put(TITLE_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT); // Asegúrate de que EXISTS_CLIENT esté definido

        System.out.println("Buscando préstamos para el cliente con ID: " + EXISTS_CLIENT);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        System.out.println("Respuesta completa: " + response);
        System.out.println("Cuerpo de la respuesta: " + response.getBody());

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        // Validar que todos los préstamos pertenecen al cliente esperado
        for (PrestamoDto prestamo : response.getBody()) {
            System.out.println("Prestamo ID: " + prestamo.getId());

        }
    }

    //Buscar un titulo y un cliente que exsitan
    @Test
    public void findExistsTitleAndClientShouldReturnPrestamos() {

        int PRESTAMOS_WITH_FILTER = 1;
        //Deveulve Ann Marie = 3 y Aventureros al tren = 2
        Map<String, Object> params = new HashMap<>();
        params.put(TITLE_PARAM, EXISTS_TITLE);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        System.out.println("Datos del préstamo:");
        response.getBody().forEach(prestamo -> {
            System.out.println("Cliente: " + prestamo.getClient().getName());
            System.out.println("Título: " + prestamo.getGame().getTitle());
        });

    }
}

