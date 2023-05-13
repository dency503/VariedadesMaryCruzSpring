package com.Rosita.store.Service;

import com.Rosita.store.Repository.CategoryRepository;
import com.Rosita.store.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryRepositoryImpl {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categoria> getAllCategories() {
        return categoryRepository.findAll();
    }
}
