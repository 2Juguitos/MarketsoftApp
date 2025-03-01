package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.RegistroCredito;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCreditoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroCreditoRepositorio extends JpaRepository<RegistroCredito, RegistroCreditoID> {
}

