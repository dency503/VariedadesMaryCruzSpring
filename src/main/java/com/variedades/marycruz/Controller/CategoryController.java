package com.variedades.marycruz.Controller;

import com.variedades.marycruz.Service.CategoryService;
import com.variedades.marycruz.models.Categoria;
import com.variedades.marycruz.record.CreaCategoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

@Tag(name = "Categorias", description = "Operaciones relacionadas con categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "Muestra la categorias")
    @GetMapping("/")
    @Transactional
    public ResponseEntity<List<Categoria>> showAllCategories() {
        List<Categoria> categories = categoryService.getAllCategories();

       return ResponseEntity.ok(categories);
    }

    @PostMapping ("categoria/add")
    @Transactional
    public ResponseEntity<String> addCategoria(@RequestBody CreaCategoria creaCategoria) {

        return categoryService.addCategory(creaCategoria);
    }
    @PutMapping ("categoria/update/{id}")
    @Transactional
    public ResponseEntity<String> updateCategoria(@PathVariable Long id,@RequestBody CreaCategoria creaCategoria) {

        return categoryService.updateCategory(id,creaCategoria);
    }

    @DeleteMapping("categoria/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {

        return categoryService.deleteCategoria(id);
    }



}