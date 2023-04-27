package com.Rosita.store.Controller;

import com.Rosita.store.Repository.ClienteRepository;

import com.Rosita.store.infra.security.TokenService;
import com.Rosita.store.models.DatosJWTToken;
import com.Rosita.store.models.LoginRequest;
import com.Rosita.store.models.Usuario;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios/login")


public class AutenticacionController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional

    public ResponseEntity<?> autenticacionUser(@RequestBody LoginRequest loginRequest, HttpSession session) {

        String username = loginRequest.username();
        String password = loginRequest.password();

        System.out.println("username"+ username);
        Authentication token = new UsernamePasswordAuthenticationToken(username, password);

        UserDetails cliente=  clienteRepository.findByUsername(username);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if ((passwordEncoder.matches(password,cliente.getPassword() ) )) {
            System.out.println("la contraseña es correcta");
        } else {
            System.out.println("la contrseña es incorrecta");
        }
        var usuarioAutenticado =authenticationManager.authenticate(token);
        var JWRtoken = tokenService.generarToken((Usuario)usuarioAutenticado.getPrincipal());


        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token1);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Guardar la autenticación en la sesión del usuario
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        boolean isLoggedIn = true;
        Map<String, Object> response = new HashMap<>();
        response.put("isLoggedIn", isLoggedIn);
        response.put("token", JWRtoken);
        return ResponseEntity.ok(response);
    }
}
