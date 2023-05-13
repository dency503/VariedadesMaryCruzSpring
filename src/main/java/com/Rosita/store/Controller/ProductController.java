package com.Rosita.store.Controller;

import com.Rosita.store.Repository.ProductoRepository;
import com.Rosita.store.Service.ProductoService;
import com.Rosita.store.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@ResponseBody
public class ProductController {
    @Autowired
 ProductoService productoService;
    @GetMapping("/product/{id}")
    public ResponseEntity<Producto> getProduct(@PathVariable Long id){
      return productoService.getProduct(id);
    }

}