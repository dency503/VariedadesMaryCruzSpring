package com.variedades.marycruz.Service;


import com.variedades.marycruz.Repository.ProductoRepository;
import com.variedades.marycruz.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void deleteProductoById(Long id) {
        productoRepository.deleteById(id);
    }
    @GetMapping("/search")
    public List<Producto> search(String name, String description, Optional<Double> minPrice, Optional<Double> maxPrice, String brand, String material) {

        return productoRepository.searchProducts(name, description, minPrice,maxPrice, brand, material);

    }

}
