package com.Rosita.store.Controller;

import com.Rosita.store.Repository.ProductoRepository;
import com.Rosita.store.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@ResponseBody
public class ProductController {
    @Autowired
    private ProductoRepository productRepository;


    // Método que maneja la solicitud de la página de producto individual
    @GetMapping("/product/{id}")
    public ResponseEntity<Producto> getProduct(@PathVariable Long id){
        System.out.println("El id es: " + id);

        // Obtener el producto del repositorio por ID
        Producto product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return new ResponseEntity<Producto>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}