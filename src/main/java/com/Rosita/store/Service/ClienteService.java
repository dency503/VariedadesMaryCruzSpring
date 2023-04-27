package com.Rosita.store.Service;

import com.Rosita.store.Repository.ClienteRepository;

import com.Rosita.store.models.Usuario;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClienteService() {
    }
    private ClienteRepository customerRepository;


    public ClienteService(ClienteRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Usuario createCliente(Usuario customer) {
        // Validar que no exista un cliente con el mismo correo electrónico
        if (customerRepository.findByEmail (customer.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        // Encriptar la contraseña antes de guardarla en la base de datos
        String encodedPassword = passwordEncoder.encode(customer.getContrasena());
        customer.setContrasena(encodedPassword);

        // Guardar el cliente en la base de datos
        return customerRepository.save(customer);
    }

    public Usuario getClienteById(Long id) {
        try {
            return customerRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario updateCliente(Usuario customer) {
        // Obtener el cliente de la base de datos
        Usuario existingUsuario = getClienteById(customer.getId());

        // Actualizar la información del cliente
        existingUsuario.setNombre(customer.getNombre());
        //existingCliente.setLastName(customer.getLastName());
        existingUsuario.setEmail(customer.getEmail());

        // Encriptar la contraseña si se ha proporcionado una nueva
        if (!StringUtils.isEmpty(customer.getContrasena())) {


            String encodedPassword = passwordEncoder.encode(customer.getContrasena());
            existingUsuario.setContrasena(encodedPassword);
        }

        // Guardar los cambios en la base de datos
        return customerRepository.save(existingUsuario);
    }

    public void deleteClienteById(Long id) {
        // Verificar que el cliente exista en la base de datos antes de eliminarlo
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
          //  throw new ChangeSetPersister.NotFoundException();
        }
    }


    public Usuario findByCorreo(String correo) {

            return customerRepository.findByEmail(correo);

    }
}

