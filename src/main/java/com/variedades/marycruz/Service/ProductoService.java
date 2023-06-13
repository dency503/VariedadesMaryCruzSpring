package com.variedades.marycruz.Service;




import com.variedades.marycruz.Repository.ProductoRepository;
import com.variedades.marycruz.models.Categoria;
import com.variedades.marycruz.models.Producto;

import com.variedades.marycruz.record.RecibirProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productRepository;
    @Autowired
    private CategoryService categoryService;


    // Método que maneja la solicitud de la página de producto individual
    public ResponseEntity<String> addProduct(RecibirProducto recibirProducto){

        CategoryService CategoryService;
        Categoria categoria=recibirProducto.categoria();

        if (categoria == null) {
            return ResponseEntity.notFound().build(); // Devolver respuesta 404 si el producto no se encuentra
        }
        productRepository.save(new Producto(recibirProducto,categoryService));
        return  ResponseEntity.ok("Success");


    }
    public Producto getProduct(Long id){


        // Obtener el producto del repositorio por ID
        Producto product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return product;
        } else {
           return null;
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        Producto product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build(); // Devolver respuesta 404 si el producto no se encuentra
        }

        product.setEliminado(true);
        productRepository.save(product); // Guardar el producto actualizado en la base de datos

        String mensaje = "Producto eliminado con éxito"; // Mensaje personalizado

        return ResponseEntity.ok(mensaje); // Devolver respuesta 200 con el mensaje personalizado
    }

    public ResponseEntity<String> updateProduct(Long id, RecibirProducto updatedProduct) {
        Producto product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build(); // Devolver respuesta 404 si el producto no se encuentra
        }

        // Actualizar los campos del producto con los valores del producto actualizado
        product.setName(updatedProduct.name());
        product.setPrice(updatedProduct.price());
        product.setImageName(updatedProduct.imageName());
        product.setUrlImage(updatedProduct.urlImage());
        product.setEsEnOferta(updatedProduct.esEnOferta());

        product.setCategoria(updatedProduct.categoria());
        // También puedes actualizar otros campos según sea necesario

        productRepository.save(product); // Guardar el producto actualizado en la base de datos

        String mensaje = "Producto actualizado con éxito"; // Mensaje personalizado

        return ResponseEntity.ok(mensaje); // Devolver respuesta 200 con el mensaje personalizado
    }

    public List<Producto> obtenerUltimasUnidades() {
        return productRepository.findAll().stream()
                .filter(producto -> producto.getUnidadesDisponibles() > 0)
                .sorted((p1, p2) -> p2.getUnidadesDisponibles() - p1.getUnidadesDisponibles())
                .collect(Collectors.toList());
    }

    public List<Producto> obtenerLoMasNuevo() {
        return productRepository.findAllByOrderByFechaLanzamientoDesc();
    }

    public List<Producto> getOfferProductos() {
        List<Producto> productos = productRepository.findAll();
        return productos.stream()
                .filter(Producto::isEsEnOferta)
                .filter(producto -> !producto.isEliminado())
                .collect(Collectors.toList());
    }



}
