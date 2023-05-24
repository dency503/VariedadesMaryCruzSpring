package com.Rosita.store.Controller;


import com.Rosita.store.Service.CartService;
import com.Rosita.store.models.Cart;
import com.Rosita.store.record.CarritoItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController


public class CarritoController {

    @Autowired
    private CartService cartService;


    @PutMapping("/carrito/agregar")
    @Transactional
    public ResponseEntity<?> agregaritem(@RequestBody CarritoItem carritoItem, Principal principal) {
        return cartService.agregarItem(carritoItem, principal);
    }

    @GetMapping("/carrito")
    @Transactional
    public ResponseEntity<Cart> verCarrito(Principal principal) {

        System.out.println(principal.getName());

        return ResponseEntity.ok(cartService.verCarrito(principal));
    }

    @PutMapping("/carrito/modificar")
    @Transactional
    public ResponseEntity<?> modificarItemDelCarrito(@RequestParam("itemId") Long itemId, @RequestParam("cantidad") int cantidad, Principal principal) {

        return modificarItemDelCarrito(itemId, cantidad, principal);
    }

    @DeleteMapping("/carrito/eliminar")
    @Transactional
    public ResponseEntity<?> eliminarDelCarrito(
            @RequestParam("itemId") Long itemId, Principal principal) {
        return cartService.eliminarDelCarrito(itemId, principal);


    }


}