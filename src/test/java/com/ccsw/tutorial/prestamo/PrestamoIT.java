package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
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

    //Listado Filtrado
    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;
    private static final String NOT_EXISTS_TITLE = "NotExists";
    private static final String EXISTS_TITLE = "Aventureros al tren";
    private static final String NEW_TITLE = "Squid Game";
    private static final Long NOT_EXISTS_CLIENT = 0L;
    private static final Long EXISTS_CLIENT = 3L;

    private static final String TITLE_PARAM = "title";
    private static final String CLIENT_ID_PARAM = "idClient";

    //Listado Páginado
    private static final int TOTAL_PRESTAMOS = 6;
    private static final int PAGE_SIZE = 5;
    public static final Long MODIFY_PRESTAMO_ID = 2L;
    public static final Long DELETE_PRESTAMO_ID = 1L;
    public static final LocalDate NEW_PRESTAMO_DATE = LocalDate.parse("2025-02-11");
    //public static final Long NEW_PRESTAMO_ID = 8L;

    public static final Long EXISTS_GAME_ID = 1L;
    public static final Long EXISTS_AUTHOR_ID = 1L;
    public static final Long EXISTS_CATEGORY_ID = 2L;
    private static final Long NEW_GAME = 1L;
    private static final Long NEW_CLIENT = 8L;
    private static final Long NEW_PRESTAMO_ID = 8L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //Listado Filtrado
    ParameterizedTypeReference<List<PrestamoDto>> responseType = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    //Lisado Paginado
    private final ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<>() {
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

    //------------------TESTS FILTRADO------------------------//
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

    //------------------TESTS PAGINADO------------------------//
    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());

        // Mostrar los datos de la respuesta
        System.out.println("Total elementos: " + response.getBody().getTotalElements());
        System.out.println("Contenido de la primera página:");

        response.getBody().getContent().forEach(prestamo -> {
            System.out.println("ID: " + prestamo.getId());
            System.out.println("Fecha préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha devolución: " + prestamo.getFechaDevolucion());
            System.out.println("Juego: " + (prestamo.getGame() != null ? prestamo.getGame().getTitle() : "N/A"));
            System.out.println("Cliente: " + (prestamo.getClient() != null ? prestamo.getClient().getName() : "N/A"));
            System.out.println("Categoría: " + (prestamo.getCategory() != null ? prestamo.getCategory().getName() : "N/A"));
            System.out.println("Autor: " + (prestamo.getAuthor() != null ? prestamo.getAuthor().getName() : "N/A"));

        });
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int expectedElements = Math.min(PAGE_SIZE, TOTAL_PRESTAMOS - PAGE_SIZE);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(expectedElements, response.getBody().getContent().size());

        System.out.println("Total elementos devueltos: " + response.getBody().getContent().size());
        System.out.println("Contenido de la segunda página:");

        response.getBody().getContent().forEach(prestamo -> {
            System.out.println("ID: " + prestamo.getId());
            System.out.println("Fecha préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha devolución: " + prestamo.getFechaDevolucion());
            System.out.println("Juego: " + (prestamo.getGame() != null ? prestamo.getGame().getTitle() : "N/A"));
            System.out.println("Cliente: " + (prestamo.getClient() != null ? prestamo.getClient().getName() : "N/A"));
            System.out.println("Categoría: " + (prestamo.getCategory() != null ? prestamo.getCategory().getName() : "N/A"));
            System.out.println("Autor: " + (prestamo.getAuthor() != null ? prestamo.getAuthor().getName() : "N/A"));

        });
    }

   /* @Test
    public void saveShouldCreateNewPrestamo() {
        long newPrestamoId = TOTAL_PRESTAMOS + 1;
        long newPrestamoSize = TOTAL_PRESTAMOS + 1;

        NEW_GAME.setTitle(NEW_TITLE);
        NEW_GAME.setId(2L);
        NEW_CLIENT.setName(NEW_CLIENT_NAME);
        NEW_CLIENT.setClientId(2L);
        PrestamoDto dto = new PrestamoDto();
        dto.setGame(NEW_GAME);
        dto.setClient(NEW_CLIENT);
        dto.setFechaPrestamo(NEW_PRESTAMO_DATE);
        dto.setFechaDevolucion(NEW_PRESTAMO_DATE);
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

    */

    //TEST PARA GURADAR PRESTAMO NO PASA
    @Test
    public void saveWithoutIdShouldCreateNewPrestamo() {

        long newPrestamoId = TOTAL_PRESTAMOS + 1;
        long newPrestamoSize = TOTAL_PRESTAMOS + 1;

        PrestamoDto dto = new PrestamoDto();
        dto.setFechaPrestamo(NEW_PRESTAMO_DATE);
        dto.setFechaDevolucion(NEW_PRESTAMO_DATE);

        GameDto dtoGame = new GameDto();
        dtoGame.setTitle("Shine");

        ClientDto dtoClient = new ClientDto();
        dtoClient.setId(7L);

        AuthorDto dtoAuthor = new AuthorDto();
        dtoAuthor.setId(8L);

        CategoryDto dtoCategory = new CategoryDto();
        dtoCategory.setName("Aventura Game");

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, (int) newPrestamoSize));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(newPrestamoSize, response.getBody().getTotalElements());

        PrestamoDto prestamo = response.getBody().getContent().stream().filter(item -> item.getId().equals(newPrestamoId)).findFirst().orElse(null);
        assertNotNull(prestamo);
        assertEquals(NEW_PRESTAMO_DATE, prestamo.getFechaPrestamo());
    }


    /*@Test
    public void modifyWithExistIdShouldModifyPrestamo() {

        PrestamoDto dto = new PrestamoDto();
        dto.setFechaPrestamo(PrestamoIT.NEW_PRESTAMO_DATE);
        dto.setFechaDevolucion(PrestamoIT.NEW_PRESTAMO_DATE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_PRESTAMO_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        PrestamoDto prestamo = response.getBody().getContent().stream().filter(item -> item.getId().equals(MODIFY_PRESTAMO_ID)).findFirst().orElse(null);
        assertNotNull(prestamo);
        assertEquals(NEW_PRESTAMO_DATE, prestamo.getFechaPrestamo());
        assertEquals(NEW_PRESTAMO_DATE, prestamo.getFechaDevolucion());


    /*@Test
    public void modifyWithExistIdShouldModifyPrestamo() {
        PrestamoDto dto = new PrestamoDto();
        dto.setFechaPrestamo(NEW_PRESTAMO_DATE);
        dto.setFechaDevolucion(NEW_PRESTAMO_DATE);

        // Seteamos todos los objetos necesarios con IDs válidos
        GameDto game = new GameDto();
        game.setId(EXISTS_GAME_ID); // Asegúrate de que este ID exista
        dto.setGame(game);

        ClientDto client = new ClientDto();
        client.setId(EXISTS_CLIENT); // Asegúrate de que este ID exista
        dto.setClient(client);

        AuthorDto author = new AuthorDto();
        author.setId(EXISTS_AUTHOR_ID); // Asegúrate de que este ID exista
        dto.setAuthor(author);

        CategoryDto category = new CategoryDto();
        category.setId(EXISTS_CATEGORY_ID); // Asegúrate de que este ID exista
        dto.setCategory(category);

        // Ejecutamos la modificación
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_PRESTAMO_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        // Buscamos el préstamo modificado
        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());

        PrestamoDto prestamo = response.getBody().getContent().stream().filter(item -> item.getId().equals(MODIFY_PRESTAMO_ID)).findFirst().orElse(null);

        assertNotNull(prestamo);
        assertEquals(NEW_PRESTAMO_DATE, prestamo.getFechaPrestamo());
        assertEquals(NEW_PRESTAMO_DATE, prestamo.getFechaDevolucion());
    }
}
*/

}
