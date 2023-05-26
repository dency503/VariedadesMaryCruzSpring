package com.variedades.marycruz.Controller;

import com.variedades.marycruz.Repository.ProductoRepository;
import com.variedades.marycruz.models.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Tag(name = "Productos", description = "Operaciones relacionadas con Productos")
public class ProductController {
    @Autowired
    private ProductoRepository productRepository;


    // Método que maneja la solicitud de la página de producto individual
    @Operation(summary = "Detalles productos")
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