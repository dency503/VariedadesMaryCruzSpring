package com.Rosita.store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Usuario user;
    private BigDecimal Impuesto;
    private BigDecimal subtotal;
    private BigDecimal Total;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    public void agregarItem(Producto producto, int cantidad) {


            Item item = new Item(producto, cantidad);
            item.setCart(this);
            items.add(item);
           // subtotal = subtotal.add(item.getSubtotal());

    }

    public void eliminarItem(Item item) {
        Cart cart = item.getCart();
        item.setCart(null);

        items.remove(item);

    }

    public Item buscarItem(Producto producto) {
        return items.stream()
                .filter(item -> item.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public BigDecimal calcularSubtotal() {

        return items.stream()
                  .map(Item::getSubtotal)
                  .filter(Objects::nonNull)
                  .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters y setters

}