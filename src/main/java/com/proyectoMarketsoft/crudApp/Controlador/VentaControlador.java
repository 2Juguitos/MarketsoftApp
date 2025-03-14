package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.DTO.request.VentaRequestDTO;
import com.proyectoMarketsoft.crudApp.DTO.response.VentaResponseDTO;
import com.proyectoMarketsoft.crudApp.Servicio.ServicioVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {

    @Autowired
    private ServicioVenta ventaServicio;

    /**
     * Endpoint para crear una nueva venta.
     * Recibe un `VentaRequestDTO` en el cuerpo y devuelve un `VentaResponseDTO`.
     */
    @PostMapping
    public ResponseEntity<?> agregarVenta(@RequestBody VentaRequestDTO ventaRequestDTO,
                                          @AuthenticationPrincipal UserDetails user) {

        if (ventaRequestDTO.getIdCliente() != null && ventaRequestDTO.getIdCliente().getIdCliente() != null) {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Se requiere autenticación para ventas con cliente.");
            }
        }

        VentaResponseDTO nuevaVenta = ventaServicio.crearVenta(ventaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }



    /**
     * Obtiene una venta por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getVentaById(@PathVariable Integer id) {
        Optional<VentaResponseDTO> venta = ventaServicio.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las ventas registradas en el sistema.
     */


    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getAllVentas() {
        List<VentaResponseDTO> ventas = ventaServicio.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {
        try {
            ventaServicio.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
