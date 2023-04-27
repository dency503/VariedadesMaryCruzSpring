package com.Rosita.store.Service;

import com.Rosita.store.Controller.Cart;
import com.Rosita.store.Repository.CartRepository;
import com.Rosita.store.models.Item;
import com.Rosita.store.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

   /* @Autowired
    private CartRepository cartRepository;

 public Cart getCartByUser(String username) {
        return cartRepository.findByUser(username);
    }

    public void addItemToCart(String username, Item item) {
        Cart cart = getCartByUser(username);
        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(String username, Long itemId) {
        Cart cart = getCartByUser(username);
        List<Item> items = cart.getItems();
        items.removeIf(item -> item.getId().equals(itemId));
        cart.setItems(items);
        cartRepository.save(cart);
    }*/

}
