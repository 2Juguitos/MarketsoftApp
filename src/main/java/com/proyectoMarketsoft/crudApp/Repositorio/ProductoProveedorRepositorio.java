package com.proyectoMarketsoft.crudApp.Repositorio;  // <-- Agrega esto arriba

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedor;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedorID;

public interface ProductoProveedorRepositorio extends JpaRepository<ProductoProveedor, ProductoProveedorID> {
}
