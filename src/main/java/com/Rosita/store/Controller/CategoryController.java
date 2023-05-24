package com.Rosita.store.Controller;

import com.Rosita.store.Service.CategoryService;
import com.Rosita.store.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@ResponseBody
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> showAllCategories() {
        List<Categoria> categories = categoryService.getAllCategories();

        return new ResponseEntity<List<Categoria>>(categories, HttpStatus.OK);
    }


}