package com.Rosita.store.Service;

import com.Rosita.store.Repository.CategoryRepository;
import com.Rosita.store.models.Categoria;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRepositoryImpl categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> showAllCategories() {
        List<Categoria> categories = categoryService.getAllCategories();
        //model.addAttribute("categories", categories);
        // Obtener el objeto Authentication de la sesión del usuario
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Utilizar el objeto Authentication para obtener los datos del usuario
        String username = authentication.getName();


        // Agregar los datos al modelo
        //model.addAttribute("username", username);

        return new ResponseEntity<List<Categoria>>(categories, HttpStatus.OK);
    }
}
