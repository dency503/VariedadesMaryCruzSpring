package com.Rosita.store.Controller;


import com.Rosita.store.Service.UsuarioService;
import com.Rosita.store.record.CreacionRecibido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/usuarios")
public class ClienteController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/nuevo-cliente")
    public String mostrarFormulario(Model model) {
        return usuarioService.mostrarFormulario(model);
    }

    @PostMapping("/registro")
    @Transactional

    public ResponseEntity<?> registro(@RequestBody CreacionRecibido creacionRecbido) throws Exception {
        return usuarioService.registro(creacionRecbido);
    }
}