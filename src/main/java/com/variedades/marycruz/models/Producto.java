package com.variedades.marycruz.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.variedades.marycruz.Service.CategoryService;
import com.variedades.marycruz.record.RecibirProducto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank
    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String urlImage;
    @Column(nullable = false)
    private boolean esEnOferta;
    @Column(nullable = false)
    private Date fechaLanzamiento;
    @Column(nullable = false)
    private int unidadesDisponibles;

    private double precioUnitario;
    private String description;
    private String brand;
    @JsonBackReference
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDetails details;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonManagedReference

    private Categoria categoria;

    private boolean eliminado;


public Producto(@Valid RecibirProducto recibirProducto, CategoryService categoryService ) {
    this.name = recibirProducto.name();
    this.price = recibirProducto.price();
    this.imageName = recibirProducto.imageName();
    this.eliminado = recibirProducto.eliminado();
    this.urlImage = recibirProducto.urlImage();

    this.categoria =recibirProducto.categoria();
    this.esEnOferta = recibirProducto.esEnOferta();
    this.unidadesDisponibles = recibirProducto.unidadesDisponibles();
    this.description = recibirProducto.description();
    this.brand = recibirProducto.brand();
}
    public Categoria categoria(CategoryService categoryService,long id) {
        // Lógica para obtener el objeto de la categoría según su id
        return categoryService.getForId(id).orElse(null);
    }

    @PrePersist
    protected void onCreate() {
        if (fechaLanzamiento == null) {
            fechaLanzamiento = new Date();
        }
    }


}
