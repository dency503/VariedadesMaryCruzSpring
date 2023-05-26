package com.variedades.marycruz.Service;

import com.variedades.marycruz.Repository.CategoryRepository;
import com.variedades.marycruz.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categoria> getAllCategories() {
        return categoryRepository.findAll();
    }
}
