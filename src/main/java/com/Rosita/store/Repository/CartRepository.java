package com.Rosita.store.Repository;

import com.Rosita.store.models.Cart;
import com.Rosita.store.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(Usuario username);
}
