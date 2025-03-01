package com.proyectoMarketsoft.crudApp.Repositorio;

import com.proyectoMarketsoft.crudApp.Modelo.TelefonoProveedor;
import com.proyectoMarketsoft.crudApp.Modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TelefonoProveedorRepositorio extends JpaRepository<TelefonoProveedor, Integer> {


    List<TelefonoProveedor> findByProveedor_IdProveedor(Integer idProveedor);


    List<TelefonoProveedor> findByTelProv(Integer telProv);
}