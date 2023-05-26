package com.variedades.marycruz.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column( nullable = false)
    private String name;

    private String description;
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    @NotBlank
    @Column(nullable = false)
    private String imageName;
    private boolean eliminado;

    @NotBlank
    @Column(nullable = false)
    private String brand;


    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductDetails> details;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnore

    private Categoria categoria;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +

                ", brand='" + brand + '\'' +
                ", details=" + details +
                ", categoria=" + categoria +
                '}';
    }

    public Producto(String nombre, BigDecimal  precio, String imageName, String descripcion) {
        this.name = nombre;
        this.price = precio;
        this.imageName = imageName;
        this.description = descripcion;
    }

}
