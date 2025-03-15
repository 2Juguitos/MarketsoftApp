package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdministradorRepositorio extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByUsername(String username);
}


