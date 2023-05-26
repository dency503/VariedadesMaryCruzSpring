package com.variedades.marycruz.Controller;


import com.variedades.marycruz.Service.UsuarioService;
import com.variedades.marycruz.record.CreacionRecibido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class ClienteController {
    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/usuarios/registro")
    @Transactional
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<?> registro(@RequestBody CreacionRecibido creacionRecbido) throws Exception {
        return usuarioService.registro(creacionRecbido);
    }
}