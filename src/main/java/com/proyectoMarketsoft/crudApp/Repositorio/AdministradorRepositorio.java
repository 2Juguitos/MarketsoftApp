package com.proyectoMarketsoft.crudApp.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Integer> {
}

