package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.Credito;
import com.proyectoMarketsoft.crudApp.Repositorio.CreditoRepositorio;
import com.proyectoMarketsoft.crudApp.Servicio.CreditoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credito")
public class CreditoControlador {

    @Autowired
    private CreditoRepositorio creditoRepositorio;


    @Autowired
    private CreditoServicio creditoServicio;

    @PostMapping
    public ResponseEntity<Credito> crearCreditoConRegistro(@RequestBody Credito credito) {
        Credito creditoCreado = creditoServicio.crearCreditoYRegistro(credito);
        return ResponseEntity.status(HttpStatus.CREATED).body(creditoCreado);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Credito> obtenerCreditoPorId(@PathVariable Integer id) {
        Optional<Credito> credito = creditoRepositorio.findById(id);
        return credito.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Credito>> obtenerTodosLosCreditos() {
        List<Credito> creditos = creditoRepositorio.findAll();
        return ResponseEntity.ok(creditos);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Credito> actualizarCredito(@PathVariable Integer id,
                                                     @RequestBody Credito credito) {
        if (!creditoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        credito.setIdCredito(id);
        Credito actualizado = creditoRepositorio.save(credito);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCredito(@PathVariable Integer id) {
        if (!creditoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        creditoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
