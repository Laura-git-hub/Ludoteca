package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/category";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>() {
    };

    @Test
    public void findAllShouldReturnAllCategories() {

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

    //Construye un nuevo objeto, recupera lista de categorías y filtra
    public static final Long NEW_CATEGORY_ID = 4L;
    public static final String NEW_CATEGORY_NAME = "CAT4";

    @Test
    public void saveWithoutIdShouldCreateNewCategory() {

        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(4, response.getBody().size());

        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CATEGORY_ID)).findFirst().orElse(null);
        //Categoria creada o no
        if (categorySearch != null) {
            System.out.println("Categoría creada: ID = " + categorySearch.getId() + ", Nombre = " + categorySearch.getName());
        } else {
            System.out.println("No se encontró la categoría con ID " + NEW_CATEGORY_ID);
        }
        assertNotNull(categorySearch);
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());
    }

    //Test de modificación
    public static final Long MODIFY_CATEGORY_ID = 3L;

    @Test
    public void modifyWithExistIdShouldModifyCategory() {

        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(3, response.getBody().size());

        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CATEGORY_ID)).findFirst().orElse(null);
        assertNotNull(categorySearch);
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName());

        System.out.println("Test modifyWithExistIdShouldModifyCategory ejecutado correctamente");
        System.out.println("ID modificado: " + MODIFY_CATEGORY_ID);
        System.out.println("Nombre actualizado: " + categorySearch.getName());
    }

    //Test para probar un resultado erroneo
    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CATEGORY_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        System.out.println("Test modifyWithNotExistIdShouldInternalError ejecutado correctamente");
        System.out.println("Intento de modificar categoría con ID inexistente: " + NEW_CATEGORY_ID);
        System.out.println("Código de estado recibido: " + response.getStatusCode());
    }

    //Test de Prueba de Borrado.
    //se invoca el método DELETE y posteriormente se comprueba que el listado tiene un tamaño de 2 (uno menos que el original).
    public static final Long DELETE_CATEGORY_ID = 2L;

    @Test
    public void deleteWithExistsIdShouldDeleteCategory() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        System.out.println("Test deleteWithExistsIdShouldDeleteCategory ejecutado correctamente");
        System.out.println("Categoría eliminada con ID: " + DELETE_CATEGORY_ID);
        System.out.println("Categorías restantes: " + response.getBody().size());
    }

    //Test que comprueba un Id no válido y devuelve un error 500
    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CATEGORY_ID, HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        System.out.println("Test deleteWithNotExistsIdShouldInternalError ejecutado correctamente");
        System.out.println("Intento de eliminar categoría con ID inexistente: " + NEW_CATEGORY_ID);
        System.out.println("Código de estado recibido: " + response.getStatusCode());
    }
}