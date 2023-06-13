package com.variedades.marycruz.Controller;

import com.variedades.marycruz.Repository.ProductoRepository;
import com.variedades.marycruz.Service.ProductoService;
import com.variedades.marycruz.models.Producto;
import com.variedades.marycruz.record.RecibirProducto;
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
@Tag(name = "Productos", description = "Operaciones relacionadas con Productos")
public class ProductController {
    @Autowired
    private ProductoRepository productRepository;
    @Autowired
    private ProductoService productoService;

    // Método que maneja la solicitud de la página de producto individual

    @Operation(summary = "Agregar productos")
    @PostMapping("/product/add")
    @Transactional
    public ResponseEntity<?> addProduct(@RequestBody RecibirProducto recibirProducto) {
    return productoService.addProduct(recibirProducto);

    }


    @Operation(summary = "Detalles productos")
    @GetMapping("/product/{id}")
    @Transactional
    public ResponseEntity<Producto> getProduct(@PathVariable Long id) {


        // Obtener el producto del repositorio por ID
        Producto product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return new ResponseEntity<Producto>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Mostrar todos productos")
    @GetMapping("/product/all")
    @Transactional
    public ResponseEntity<List<Producto>> allProduct() {


        // Obtener el producto del repositorio por ID
        List<Producto> product = productRepository.findAll();

        if (product != null) {
            return  ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Actualizar productos")
    @PutMapping("/product/{id}")
    @Transactional
    public ResponseEntity<String> updateProduct(@RequestBody RecibirProducto recibirProducto, @PathVariable Long id) {


       return productoService.updateProduct(id,recibirProducto);
    }

    @Operation(summary = "Eliminar productos")
    @DeleteMapping("/product/{id}")
    @Transactional
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {


        return productoService.deleteProduct(id);
    }

    @Operation(summary = "Mostrar nuevos productos")

    @GetMapping("/product/new")
    @Transactional
    public ResponseEntity<List<Producto>> newProduct() {


        // Obtener el producto del repositorio por ID
        List<Producto> product = productRepository.findAllByOrderByFechaLanzamientoDesc();

        if (product != null) {
            return ResponseEntity.ok().header("Mensaje", "Productos nuevos").body(product);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/offers")
    @Transactional
    public ResponseEntity<List<Producto>> Offer() {



        return ResponseEntity.ok(productoService.getOfferProductos());
    }

}