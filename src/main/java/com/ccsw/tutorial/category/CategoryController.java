package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ccsw
 *
 */
@Tag(name = "Category", description = "API of Category")
@RequestMapping(value = "/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    private long SEQUENCE = 1;
    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * Método para recuperar todas las categorias
     *
     * @return {@link List} de {@link CategoryDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CategoryDto> findAll() {

        return new ArrayList<CategoryDto>(this.categories.values());
    }

    /**
     * Método para crear o actualizar una categoria
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Category")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {

        CategoryDto category;

        if (id == null) {
            category = new CategoryDto();
            category.setId(this.SEQUENCE++);
            this.categories.put(category.getId(), category);
        } else {
            category = this.categories.get(id);
        }

        category.setName(dto.getName());
    }

    /**
     * Método para borrar una categoria
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.categories.remove(id);
    }
}