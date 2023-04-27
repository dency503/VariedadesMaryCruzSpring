package com.Rosita.store.Controller;


import com.Rosita.store.Repository.CartRepository;
import com.Rosita.store.Repository.ItemCarritoRepository;
import com.Rosita.store.Repository.ProductoRepository;
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
    private static final BigDecimal IMPUESTO = new BigDecimal("0.13");
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Autowired
    private CartRepository carritoRepository;

    @Autowired
    private AutenticacionService usuarioDetailsService;



    @PutMapping("/carrito/agregar")
    @Transactional
    public ResponseEntity<String> agregarItem(@RequestBody CarritoItem carritoItem, Principal principal) {
        // Buscar el producto en la base de datos..

        Producto productoEnBD = productoRepository.findById(carritoItem.productoId()).orElseThrow(RuntimeException::new);


        Usuario usuario = (Usuario) usuarioDetailsService.loadUserByUsername(principal.getName());

// Obtener el carrito del usuario a través de su sesión...
        Cart carritoUsuario = usuario.getCart();
        if (carritoUsuario == null) {
            carritoUsuario = new Cart();
            carritoUsuario.setUser(usuario);
            usuario.setCart(carritoUsuario);
        }

// Buscar el item existente en el carrito...
        Item itemExistente = carritoUsuario.buscarItem(productoEnBD);
        if (itemExistente != null) {
            // Si ya existe un Item para el producto, se actualiza su cantidad...
            int cantidad = itemExistente.getCantidad() + carritoItem.cantidad();
            itemExistente.setCantidad(cantidad);
            BigDecimal bigDecimal = itemExistente.getProducto().getPrice();
            BigDecimal resultado = bigDecimal.multiply(BigDecimal.valueOf(cantidad));
            System.out.println(resultado);
            itemExistente.setSubtotal(resultado);
        } else {
            // Si no existe un Item para el producto, se agrega el producto al carrito...
            carritoUsuario.agregarItem(productoEnBD, carritoItem.cantidad());

        }

// Calcular el subtotal y el total del carrito...
        BigDecimal subtotal = carritoUsuario.calcularSubtotal();
        BigDecimal total = subtotal.add(subtotal.multiply(IMPUESTO)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal impuesto = subtotal.multiply(IMPUESTO).setScale(2, RoundingMode.HALF_UP);
        carritoUsuario.setImpuesto(impuesto);
        System.out.println("El impuesto es"+impuesto);
// Actualizar el carrito...
        carritoUsuario.setSubtotal(subtotal);
        carritoUsuario.setTotal(total);

// Guardar el carrito en la base de datos...
        carritoRepository.save(carritoUsuario);

        return ResponseEntity.ok("Success");}

    @GetMapping("/carrito")
    public String verCarrito1(Model model, Principal principal) {
        Usuario usuario = (Usuario) usuarioDetailsService.loadUserByUsername(principal.getName());
        Cart carritoUsuario = carritoRepository.findByUser(usuario);

        model.addAttribute("carritos", carritoUsuario);
        return "carrito1";
    }

    @PutMapping("/carrito/modificar")
    @Transactional
    public String modificarItemDelCarrito(@RequestParam("itemId") Long itemId, @RequestParam("cantidad") int cantidad, Principal principal) {
        Producto productoEnBD = productoRepository.findById(itemId).orElseThrow(RuntimeException::new);
        Usuario usuario = (Usuario) usuarioDetailsService.loadUserByUsername(principal.getName());
        Cart carritoUsuario = carritoRepository.findByUser(usuario);
        // Comprobar si el usuario tiene item del producto existente
        Item itemExistente = null;
        for (Item item : carritoUsuario.getItems()) {
            if (item.getProducto().equals(productoEnBD)) {
                itemExistente = item;
                System.out.println("Existente");
                break;
            }
        }
        if (itemExistente != null) {
            // Si ya existe un Item para el producto, se actualiza su cantidad
            itemExistente.setCantidad(cantidad);

            BigDecimal bigDecimal = itemExistente.getProducto().getPrice();
            BigDecimal resultado = bigDecimal.multiply(BigDecimal.valueOf(cantidad));
            System.out.println(resultado);
            itemExistente.setSubtotal(resultado);
        }

        BigDecimal subtotal = carritoUsuario.getItems().stream()
                .map(Item::getSubtotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal total = subtotal.add(subtotal.multiply(IMPUESTO)).setScale(2, RoundingMode.HALF_UP);
        // Calcular el impuesto a partir del subtotal
        BigDecimal impuesto = subtotal.multiply(IMPUESTO).setScale(2, RoundingMode.HALF_UP);

        // Actualizar el carrito...
        carritoUsuario.setSubtotal(subtotal);
        carritoUsuario.setTotal(total);
        carritoUsuario.setImpuesto(impuesto);
        System.out.println("El impuesto es"+impuesto);

        // Guardar el carrito en la base de datos...
        carritoRepository.save(carritoUsuario);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar")
    @Transactional
    public String eliminarDelCarrito(
            @RequestParam("itemId") Long itemId,
            Principal principal
    ) {
        try {
            // Verificar si el usuario es un cliente
            Usuario usuario = (Usuario) usuarioDetailsService.loadUserByUsername(principal.getName());
            // Obtener el carrito del usuario
            Cart carritoUsuario = carritoRepository.findByUser(usuario);

            // Buscar el item en el carrito
            Optional<Item> itemAEliminar = carritoUsuario.getItems().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();

            if (itemAEliminar.isPresent()) {
                // Eliminar el item del carrito
                carritoUsuario.eliminarItem(itemAEliminar.get());

                // Calcular el subtotal y el total
                BigDecimal subtotal = carritoUsuario.getItems().stream()
                        .map(Item::getSubtotal)
                        .filter(Objects::nonNull)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal impuesto = subtotal.multiply(IMPUESTO);
                BigDecimal total = subtotal.add(impuesto).setScale(2, RoundingMode.HALF_UP);

                // Actualizar el carrito
                carritoUsuario.setSubtotal(subtotal);
                carritoUsuario.setImpuesto(impuesto);
                carritoUsuario.setTotal(total);

                // Guardar el carrito en la base de datos
                carritoRepository.save(carritoUsuario);
            }

            // Redirigir a la página de carrito
            return "redirect:/carrito";

        } catch (Exception e) {
            // Manejar las excepciones y mostrar un mensaje de error
            String mensajeError = "No se pudo eliminar el producto del carrito. " + e.getMessage();
            return "redirect:/carrito1?error=" + mensajeError;
        }
    }

}