package com.variedades.marycruz.Controller;


import com.variedades.marycruz.Service.CartService;
import com.variedades.marycruz.models.Cart;
import com.variedades.marycruz.record.CarritoItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/carrito/")
@Tag(name = "Carrito", description = "Operaciones relacionadas con Carrito")

public class CarritoController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "agregar item al carrito")
    @PutMapping("agregar")
    @Transactional
    public ResponseEntity<?> agregaritem(@RequestBody CarritoItem carritoItem, Principal principal) {
        return cartService.agregarItem(carritoItem, principal);
    }

    @GetMapping("/")
    @Transactional
    @Operation(summary = "ver el carrito")
    public ResponseEntity<Cart> verCarrito(Principal principal) {

        System.out.println(principal.getName());

        return ResponseEntity.ok(cartService.verCarrito(principal));
    }

    @PutMapping("modificar")
    @Transactional
    @Operation(summary = "modificar item al carrito")
    public ResponseEntity<?> modificarItemDelCarrito(@RequestParam("itemId") Long itemId, @RequestParam("cantidad") int cantidad, Principal principal) {

        return modificarItemDelCarrito(itemId, cantidad, principal);
    }

    @DeleteMapping("/eliminar")
    @Transactional
    @Operation(summary = "eliminar item al carrito")
    public ResponseEntity<?> eliminarDelCarrito(
            @RequestParam("itemId") Long itemId, Principal principal) {
        return cartService.eliminarDelCarrito(itemId, principal);


    }


}