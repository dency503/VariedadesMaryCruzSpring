package com.Rosita.store.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rosita.store.Repository.ProductoRepository;
import com.Rosita.store.models.Producto;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ProductoService {

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
