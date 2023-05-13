package com.Rosita.store.Service;


import com.Rosita.store.Repository.ClienteRepository;
import com.Rosita.store.infra.security.TokenService;
import com.Rosita.store.models.LoginRequest;
import com.Rosita.store.models.Usuario;
import com.Rosita.store.record.CreacionRecibido;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;


    public ResponseEntity<?> autenticacionUser(@RequestBody LoginRequest loginRequest, HttpSession session) {

        String username = loginRequest.username();
        String password = loginRequest.password();

        System.out.println("username" + username);
        Authentication token = new UsernamePasswordAuthenticationToken(username, password);

        UserDetails cliente = clienteRepository.findByUsername(username);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if ((passwordEncoder.matches(password, cliente.getPassword()))) {
            System.out.println("la contraseña es correcta");
        } else {
            System.out.println("la contrseña es incorrecta");
        }
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWRtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());


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

    @Autowired
    private ClienteRepository usuarioRepository;

    @Autowired
    private UserServiceImpl userService;
    private Usuario usuario;


    public String mostrarFormulario(Model model) {

        return "register"; // nombre de la página HTML que contiene el formulario
    }


    public ResponseEntity<?> registro(@RequestBody CreacionRecibido creacionRecbido
    ) throws Exception {
        // Validar que no haya otro usuario registrado con el mismo correo electrónico

        System.out.println(creacionRecbido.password());
        Usuario usuario = new Usuario(creacionRecbido.username(), creacionRecbido.email(), creacionRecbido.password());
        if ((usuarioRepository.findByEmail(creacionRecbido.email()) != null) && (usuarioRepository.findByUsername(creacionRecbido.username()) != null)) {
            return null;
        }


        userService.register(usuario);

        boolean isLoggedIn = true;
        Map<String, Object> response = new HashMap<>();
        response.put("isLoggedIn", isLoggedIn);

        return ResponseEntity.ok(response);
    }

}
