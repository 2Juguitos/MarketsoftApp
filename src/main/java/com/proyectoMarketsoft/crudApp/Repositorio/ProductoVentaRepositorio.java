package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoVentaRepositorio extends JpaRepository<ProductoVenta, Integer> {
}
