package com.variedades.marycruz.Repository;

import com.variedades.marycruz.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Cart, Long> {
}
