package com.variedades.marycruz.Repository;

import com.variedades.marycruz.models.Cart;
import com.variedades.marycruz.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(Usuario username);
}
