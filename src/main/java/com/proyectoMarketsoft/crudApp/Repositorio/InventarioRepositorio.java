package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface InventarioRepositorio extends JpaRepository<Inventario, Integer> {

    @Query("SELECT COALESCE(SUM(i.precio), 0) FROM inventario i WHERE i.producto.idProducto = :idProducto")
    BigDecimal sumarPreciosPorProducto(@Param("idProducto") Integer idProducto);

}
