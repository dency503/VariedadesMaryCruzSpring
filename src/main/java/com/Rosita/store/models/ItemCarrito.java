package com.Rosita.store.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@ManyToOne
@JoinColumn(name = "producto_id")
        private Producto producto;
        private int cantidad;
        @OneToOne
        private Usuario usuario;
        private boolean active;
       private  double subtotal;

        public ItemCarrito(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }


    public void setActivo(boolean b) {
            active= b;
    }
}


