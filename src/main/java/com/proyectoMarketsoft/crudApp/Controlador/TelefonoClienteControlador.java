package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.TelefonoCliente;
import com.proyectoMarketsoft.crudApp.Repositorio.TelefonoClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tlfcliente")
public class TelefonoClienteControlador {

    @Autowired
    private TelefonoClienteRepositorio tlfClienteRepo;

    @PostMapping
    public ResponseEntity<TelefonoCliente> crearTelefonoCliente(@RequestBody TelefonoCliente telefonoCliente) {
        TelefonoCliente nuevoTelefono = tlfClienteRepo.save(telefonoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTelefono);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefonoCliente> obtenerTelefonoClientePorId(@PathVariable Integer id) {
        Optional<TelefonoCliente> telefonoCliente = tlfClienteRepo.findById(id);
        return telefonoCliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TelefonoCliente>> obtenerTodosTelefonoClientes() {
        List<TelefonoCliente> lista = tlfClienteRepo.findAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefonoCliente> actualizarTelefonoCliente(@PathVariable Integer id,
                                                                     @RequestBody TelefonoCliente telefonoCliente) {
        if (!tlfClienteRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        telefonoCliente.setIdTlfCliente(id);
        TelefonoCliente actualizado = tlfClienteRepo.save(telefonoCliente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTelefonoCliente(@PathVariable Integer id) {
        if (!tlfClienteRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tlfClienteRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
