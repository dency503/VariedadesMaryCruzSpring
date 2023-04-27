package com.Rosita.store.Repository;

import com.Rosita.store.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Usuario, Long> {
     UserDetails findByUsername(String username);

    Usuario findByEmail(String correo);
}