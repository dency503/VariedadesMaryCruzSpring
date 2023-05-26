package com.variedades.marycruz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @NotBlank(message = "El usuario es obligatorio")
    private String username;

    private String nombre;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email es inválido")
    private String email;

    private String apellido;

    private String telefono;
    @NotBlank(message = "Contraseña es obligatorio")

    private String contrasena;
    @OneToOne
    private Cart cart;

    public Usuario(String username, String email, String contrasena) {
        this.username = username;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(String nombre, String correo, String apellido, String contrasena, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = correo;


        this.telefono = telefono;


        this.contrasena = contrasena;

    }


    public boolean isPresent() {
        return true;
    }
}