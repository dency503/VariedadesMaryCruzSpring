package com.variedades.marycruz.Controller;

import com.variedades.marycruz.Service.UsuarioService;
import com.variedades.marycruz.record.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController

@RequestMapping("/api/usuarios/")

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class AutenticacionController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("login")
    @Transactional
    @Operation(summary = "Iniciar session usuario", description = "Iniciar sesion del usuario con la información proporcionada")
    public ResponseEntity<?> autenticacionUser(@RequestBody LoginRequest loginRequest, HttpSession session  ) {
        return usuarioService.autenticacionUser(loginRequest, session);
    }


}