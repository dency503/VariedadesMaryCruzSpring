package com.variedades.marycruz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    private int cantidad;

    private BigDecimal subtotal;

@ManyToOne(cascade = CascadeType.ALL)
@JsonBackReference
private  Cart cart;

    // Constructor con parámetros
    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrice().multiply(BigDecimal.valueOf(cantidad));
    }



    // Getter y setter para id, producto, cantidad y subtotal
    // ...

}