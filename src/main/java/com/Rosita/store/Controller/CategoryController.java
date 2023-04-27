package com.Rosita.store.Controller;

import com.Rosita.store.Repository.CategoryRepository;
import com.Rosita.store.Service.CategoryService;
import com.Rosita.store.models.Categoria;
import com.Rosita.store.models.Producto;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@ResponseBody
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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