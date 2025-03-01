package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import com.proyectoMarketsoft.crudApp.Repositorio.VentaRepositorio;
import com.proyectoMarketsoft.crudApp.Servicio.ServicioVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {



    @Autowired
    private ServicioVenta ventaServicio;

    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaServicio.crearVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Optional<Venta> venta = ventaServicio.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaServicio.getAllVentas();
    }
}