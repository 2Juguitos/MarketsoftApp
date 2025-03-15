package com.proyectoMarketsoft.crudApp.Servicio;


import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepositorio administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador admin = administradorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado: " + username));
        System.out.println("Cargando admin: " + admin.getUsername() + " con password: " + admin.getPassword());
        return new AdministradorUserDetails(admin);
    }
}

