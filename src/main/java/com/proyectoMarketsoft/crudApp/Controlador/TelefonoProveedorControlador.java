package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.TelefonoProveedor;
import com.proyectoMarketsoft.crudApp.Modelo.Proveedor;
import com.proyectoMarketsoft.crudApp.Repositorio.TelefonoProveedorRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/telefonosprov")
public class TelefonoProveedorControlador {

    @Autowired
    private TelefonoProveedorRepositorio telefonoProveedorRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;


    @GetMapping
    public List<TelefonoProveedor> getAllTelefonos() {
        return telefonoProveedorRepositorio.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TelefonoProveedor> getTelefonoById(@PathVariable Integer id) {
        return telefonoProveedorRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<TelefonoProveedor> createTelefono(@RequestBody TelefonoProveedor telProv) {
        if (telProv.getProveedor() != null) {
            Optional<Proveedor> proveedor = proveedorRepositorio.findById(telProv.getProveedor().getIdProveedor());
            if (proveedor.isPresent()) {
                telProv.setProveedor(proveedor.get());
                TelefonoProveedor savedTelProv = telefonoProveedorRepositorio.save(telProv);
                return ResponseEntity.status(201).body(savedTelProv);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TelefonoProveedor> updateTelefono(@PathVariable Integer id, @RequestBody TelefonoProveedor telProvActualizado) {
        Optional<TelefonoProveedor> telefonoExistente = telefonoProveedorRepositorio.findById(id);

        if (telefonoExistente.isPresent()) {
            TelefonoProveedor telefono = telefonoExistente.get();
            telefono.setTelProv(telProvActualizado.getTelProv());

            if (telProvActualizado.getProveedor() != null) {
                Optional<Proveedor> proveedor = proveedorRepositorio.findById(telProvActualizado.getProveedor().getIdProveedor());
                proveedor.ifPresent(telefono::setProveedor);
            }

            TelefonoProveedor telefonoActualizado = telefonoProveedorRepositorio.save(telefono);
            return ResponseEntity.ok(telefonoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefono(@PathVariable Integer id) {
        if (telefonoProveedorRepositorio.existsById(id)) {
            telefonoProveedorRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<TelefonoProveedor>> getTelefonosByProveedor(@PathVariable Integer idProveedor) {
        List<TelefonoProveedor> telefonos = telefonoProveedorRepositorio.findByProveedor_IdProveedor(idProveedor);
        if (telefonos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(telefonos);
    }
}
