package com.Rosita.store.Controller;


import com.Rosita.store.Repository.CartRepository;
import com.Rosita.store.Repository.ItemCarritoRepository;
import com.Rosita.store.Repository.ProductoRepository;
import com.Rosita.store.Service.CartService;
import com.Rosita.store.infra.security.AutenticacionService;
import com.Rosita.store.models.CarritoItem;
import com.Rosita.store.models.Usuario;
import com.Rosita.store.models.Item;
import com.Rosita.store.models.Producto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;

import java.util.Objects;
import java.util.Optional;

@RestController

@ResponseBody

public class CarritoController {
    @Autowired
   CartService cartService;

    @PutMapping("/carrito/agregar")
    @Transactional
    public ResponseEntity<String> agregarItem(@RequestBody CarritoItem carritoItem, Principal principal) {
       return cartService.agregarItem(carritoItem,principal);
    }

    @GetMapping("/carrito")
    public String verCarrito1(Model model, Principal principal) {
        return cartService.verCarrito1(model,principal);
    }
    @PutMapping("/carrito/modificar")
    @Transactional
    public String modificarItemDelCarrito(@RequestParam("itemId") Long itemId, @RequestParam("cantidad") int cantidad, Principal principal){
        return cartService.modificarItemDelCarrito(itemId,cantidad,principal);
    }

    @PostMapping("/carrito/eliminar")
    @Transactional
    public String eliminarDelCarrito(
            @RequestParam("itemId") Long itemId,
            Principal principal
    ){
        return cartService.eliminarDelCarrito(itemId,principal);
    }

}