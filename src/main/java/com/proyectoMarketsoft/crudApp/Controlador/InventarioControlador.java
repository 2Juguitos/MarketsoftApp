package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Inventario;
import com.proyectoMarketsoft.crudApp.Servicio.InventarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin("*") // Permite solicitudes desde cualquier origen
public class InventarioControlador {

    private final InventarioServicio inventarioServicio;

    public InventarioControlador(InventarioServicio inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }


    @GetMapping
    public List<Inventario> listarInventarios() {
        return inventarioServicio.listarTodos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable Integer id) {
        Optional<Inventario> inventario = inventarioServicio.buscarPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Inventario crearInventario(@RequestBody Inventario inventario) {
        return inventarioServicio.guardar(inventario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Integer id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioServicio.actualizar(id, inventario);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Integer id) {
        inventarioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
