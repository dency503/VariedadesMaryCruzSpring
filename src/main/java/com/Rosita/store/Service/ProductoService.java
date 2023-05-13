package com.Rosita.store.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Rosita.store.Repository.ProductoRepository;
import com.Rosita.store.models.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productRepository;


    // Método que maneja la solicitud de la página de producto individual

    public ResponseEntity<Producto> getProduct(Long id){
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
