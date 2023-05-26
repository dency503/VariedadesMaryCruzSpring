package com.variedades.marycruz.Service;

import com.variedades.marycruz.Repository.ClienteRepository;
import com.variedades.marycruz.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  {

    @Autowired
    private ClienteRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Usuario user) throws Exception {


        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setUsername(user.getUsername());
        user.setContrasena (encodedPassword);


        userRepository.save(user);
    }
}
