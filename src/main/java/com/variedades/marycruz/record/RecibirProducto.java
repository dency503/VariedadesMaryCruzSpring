package com.variedades.marycruz.record;

import com.variedades.marycruz.models.Categoria;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record RecibirProducto(
        @NotBlank
        @Column(nullable = false)
        String name,
        @Column(nullable = false)
        BigDecimal price,

        String imageName,
        @NotBlank
        @Column(nullable = false)
        String urlImage,
        @NotBlank
        Categoria categoria,
        boolean eliminado,
        boolean esEnOferta,
        int unidadesDisponibles,
        String description,
        String brand
        ) {


}

