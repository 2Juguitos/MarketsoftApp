package com.proyectoMarketsoft.crudApp.Servicio;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Modelo.Inventario;
import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import com.proyectoMarketsoft.crudApp.Repositorio.InventarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class InventarioServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    private final InventarioRepositorio inventarioRepositorio;

    public InventarioServicio(InventarioRepositorio inventarioRepositorio) {
        this.inventarioRepositorio = inventarioRepositorio;
    }

    public List<Inventario> listarTodos() {
        return inventarioRepositorio.findAll();
    }

    public Optional<Inventario> buscarPorId(Integer id) {
        return inventarioRepositorio.findById(id);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepositorio.save(inventario);
    }

    public Inventario actualizar(Integer id, Inventario inventario) {
        if (inventarioRepositorio.existsById(id)) {
            inventario.setId(id); // Asegurar que mantenga el mismo ID
            return inventarioRepositorio.save(inventario);
        } else {
            throw new RuntimeException("Inventario no encontrado");
        }
    }

    public void eliminar(Integer id) {
        inventarioRepositorio.deleteById(id);
    }

    public Inventario crearInventario(Inventario inventario) {
        // Recuperar la instancia persistida de Producto
        Optional<Producto> prodOpt = productoRepositorio.findById(inventario.getProducto().getIdProducto());
        Optional<Administrador> adminOpt = administradorRepositorio.findById(inventario.getAdministrador().getIdAdmin());

        if (!prodOpt.isPresent() || !adminOpt.isPresent()) {
            throw new RuntimeException("Producto o Administrador no encontrados");
        }

        inventario.setProducto(prodOpt.get());
        inventario.setAdministrador(adminOpt.get());

        // Lógica adicional...
        if (inventario.getStock() == null || inventario.getStock() <= 0) {
            inventario.setProductoDisponible(false);
        } else {
            inventario.setProductoDisponible(true);
        }

        return inventarioRepositorio.save(inventario);
    }

}

