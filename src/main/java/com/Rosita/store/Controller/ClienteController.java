package com.Rosita.store.Controller;


import com.Rosita.store.Repository.ClienteRepository;
import com.Rosita.store.Service.UserServiceImpl;
import com.Rosita.store.Service.UsuarioService;
import com.Rosita.store.models.Usuario;
import com.Rosita.store.record.CreacionRecibido;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*
@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    private Cliente _cliente;

    @GetMapping("/nuevo-cliente")
    public String mostrarFormulario(Model model) {

        return "clienteagregado"; // nombre de la página HTML que contiene el formulario
    }




    @PostMapping(value = "/clientes")
    public String service( @RequestParam String nombre, @RequestParam String email,@RequestParam String apellido, @RequestParam String telefono, @RequestParam String contraseña ,Model model, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            // Validar el token CSRF

            String valorToken = token.getToken();
            String valorTokenSolicitud = request.getParameter("_csrf");
            // ...
            System.out.println("Se inicio");
            Cliente cliente = new Cliente(nombre, email, apellido, contraseña,telefono);
            clienteService.createCliente(cliente);
            System.out.println("Cliente " + nombre + "email " + email +"Apellido " + apellido + "contraseña " + contraseña + " telefono " + telefono);
            model.addAttribute("cliente", cliente);
            return "clienteagregado"; // nombre de la página HTML para mostrar la confirmación
            // ...

        } else {
            // Si el token no es válido, mostrar un mensaje de error al usuario
            // ...
            return "error";
        }



    }

    @PostMapping("/clientes1")
    public String service1() {
        return "clienteagregado";
    }
}*/

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;

@RestController
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

    public ResponseEntity<?> registro( @RequestBody CreacionRecibido creacionRecbido) throws Exception {
        return usuarioService.registro(creacionRecbido);
    }
}