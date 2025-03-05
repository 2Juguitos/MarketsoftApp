package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoProveedorRepositorio extends JpaRepository<ProductoProveedor, Integer> {

}
