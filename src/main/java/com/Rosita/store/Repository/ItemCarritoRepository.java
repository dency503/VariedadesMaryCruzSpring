package com.Rosita.store.Repository;


import com.Rosita.store.models.Usuario;
import com.Rosita.store.models.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito,Long> {
    List<ItemCarrito>  findByUsuario(Usuario usuario);

}
