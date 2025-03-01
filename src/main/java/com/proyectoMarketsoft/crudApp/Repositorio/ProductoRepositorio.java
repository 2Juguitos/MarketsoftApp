package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

    //
    List<Producto> findByCategoria_IdCategoria(Integer idCategoria);

    //
    List<Producto> findByAdministrador_IdAdmin(Integer idAdmin);

    //
    List<Producto> findByNombreProdContainingIgnoreCase(String nombre);



}
