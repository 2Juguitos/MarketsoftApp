package com.proyectoMarketsoft.crudApp.Controlador;



import com.proyectoMarketsoft.crudApp.Modelo.Proveedor;
import com.proyectoMarketsoft.crudApp.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorControlador {

    @Autowired
    private ProveedorRepositorio proveedorrepositorio;


    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorrepositorio.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable int id) {
        return proveedorrepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Proveedor> saveProveedor(@RequestBody Proveedor proveedor) {
        Proveedor savedProveedor = proveedorrepositorio.save(proveedor);
        return ResponseEntity.status(201).body(savedProveedor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedorActualizado) {
        if (proveedorrepositorio.existsById(id)) {

            proveedorActualizado.setIdProveedor(id);
            Proveedor proveedorGuardado = proveedorrepositorio.save(proveedorActualizado);
            return ResponseEntity.ok(proveedorGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public void eliminarProveedor(@PathVariable Integer id) {
        proveedorrepositorio.deleteById(id);
    }

    // Obtener proveedores por ID del Administrador
    @GetMapping("/administrador/{idAdmin}")
    public ResponseEntity<List<Proveedor>> getProveedoresByAdministrador(@PathVariable int idAdmin) {
        List<Proveedor> proveedores = proveedorrepositorio.findByAdministrador_IdAdmin(idAdmin);
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

}

