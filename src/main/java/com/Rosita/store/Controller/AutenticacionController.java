package com.Rosita.store.Controller;

import com.Rosita.store.Repository.ClienteRepository;

import com.Rosita.store.Service.UsuarioService;
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



public class AutenticacionController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    @RequestMapping("/api/usuarios/login")
    public ResponseEntity<?> autenticacionUser(@RequestBody LoginRequest loginRequest, HttpSession session){
        return usuarioService.autenticacionUser(loginRequest,session);
    }


}
