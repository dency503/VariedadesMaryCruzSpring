package com.variedades.marycruz.Service;

import com.variedades.marycruz.Repository.CategoryRepository;
import com.variedades.marycruz.models.Categoria;
import com.variedades.marycruz.models.Producto;
import com.variedades.marycruz.record.CreaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categoria> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Optional<Categoria> getForId(long id) {
        return categoryRepository.findById(id);
    }

    public ResponseEntity<String> addCategory(CreaCategoria creaCategoria) {
       categoryRepository.save(new Categoria(creaCategoria));
        return  ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> deleteCategoria(Long id) {
        categoryRepository.deleteById(id);
        return  ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> updateCategory(Long id,CreaCategoria creaCategoria) {
        Categoria categoria = categoryRepository.findById(id).orElse(null);

        if (categoria == null) {
            return ResponseEntity.notFound().build(); // Devolver respuesta 404 si el producto no se encuentra
        }

        // Actualizar los campos del producto con los valores del producto actualizado
        categoria.setName(creaCategoria.name());
        // También puedes actualizar otros campos según sea necesario

        categoryRepository.save(categoria); // Guardar el producto actualizado en la base de datos

        String mensaje = "Producto actualizado con éxito"; // Mensaje personalizado

        return ResponseEntity.ok(mensaje);
    }
}
