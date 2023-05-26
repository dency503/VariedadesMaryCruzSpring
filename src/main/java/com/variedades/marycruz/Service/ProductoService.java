package com.variedades.marycruz.Service;




import com.variedades.marycruz.Repository.ProductoRepository;
import com.variedades.marycruz.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productRepository;


    // Método que maneja la solicitud de la página de producto individual

    public Producto getProduct(Long id){
        System.out.println("El id es: " + id);

        // Obtener el producto del repositorio por ID
        Producto product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return product;
        } else {
           return null;
        }
    }

}
