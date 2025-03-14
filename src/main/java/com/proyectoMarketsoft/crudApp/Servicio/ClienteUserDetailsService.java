package com.proyectoMarketsoft.crudApp.Servicio;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import com.proyectoMarketsoft.crudApp.Servicio.ClienteUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepositorio clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        System.out.println(" Contraseña almacenada en la BD: " + cliente.getPassword());
        return new ClienteUserDetails(cliente);
    }

}
