
package com.proyectoMarketsoft.crudApp.Repositorio;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    Cliente findByUsername(String username);
}

