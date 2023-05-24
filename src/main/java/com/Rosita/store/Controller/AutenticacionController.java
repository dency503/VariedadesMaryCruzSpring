package com.Rosita.store.Controller;

import com.Rosita.store.Service.UsuarioService;
import com.Rosita.store.record.LoginRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/usuarios/login")


public class AutenticacionController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional

    public ResponseEntity<?> autenticacionUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return usuarioService.autenticacionUser(loginRequest, session);
    }


}