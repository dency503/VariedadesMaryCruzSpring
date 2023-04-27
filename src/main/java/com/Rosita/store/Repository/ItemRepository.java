package com.Rosita.store.Repository;

import com.Rosita.store.Controller.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Cart, Long> {
}
