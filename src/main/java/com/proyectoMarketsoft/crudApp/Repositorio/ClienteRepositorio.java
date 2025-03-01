
package com.proyectoMarketsoft.crudApp.Repositorio;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {



}
