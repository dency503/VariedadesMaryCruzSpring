package com.variedades.marycruz.Repository;


import com.variedades.marycruz.models.Usuario;
import com.variedades.marycruz.models.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito,Long> {
    List<ItemCarrito>  findByUsuario(Usuario usuario);

}
