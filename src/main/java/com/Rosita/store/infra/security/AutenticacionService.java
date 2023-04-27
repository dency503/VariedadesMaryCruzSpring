package com.Rosita.store.infra.security;

import com.Rosita.store.Repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepositoryRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails cliente =  clienteRepositoryRepository.findByUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return cliente;

    }
}
