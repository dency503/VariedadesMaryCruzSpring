package com.variedades.marycruz.Repository;

import com.variedades.marycruz.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNameContainingAndPriceBetween(
            String name,

            Double minPrice,
            Double maxPrice

    );

    default List<Producto> searchProducts(String name, String description, Optional<Double> minPrice, Optional<Double> maxPrice, String brand, String material) {


        System.out.println("Maximo" + maxPrice.orElse(999.99));
        return findByNameContainingAndPriceBetween(
                name,

                minPrice.orElse(0.00),
                maxPrice.orElse(999.99)


        );


    }

    Producto findByIdAndEliminadoIsFalse(Long productoId);

    List<Producto> findAllByOrderByFechaLanzamientoDesc();
}



