package com.variedades.marycruz.Controller;

import com.variedades.marycruz.Service.CategoryService;
import com.variedades.marycruz.models.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

@Tag(name = "Categorias", description = "Operaciones relacionadas con categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "Muestra la categorias")
    @GetMapping("/")
    public ResponseEntity<List<Categoria>> showAllCategories() {
        List<Categoria> categories = categoryService.getAllCategories();

        return new ResponseEntity<List<Categoria>>(categories, HttpStatus.OK);
    }


}