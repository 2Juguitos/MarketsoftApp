package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCredito;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCreditoID;
import com.proyectoMarketsoft.crudApp.Repositorio.RegistroCreditoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regcredito")
public class RegistroCreditoControlador {

    @Autowired
    private RegistroCreditoRepositorio regCreditoRepo;


    @PostMapping
    public ResponseEntity<RegistroCredito> crearRegistro(@RequestBody RegistroCredito registroCredito) {
        RegistroCredito nuevoRegistro = regCreditoRepo.save(registroCredito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
    }


    @GetMapping("/{creditoID}/{adminID}")
    public ResponseEntity<RegistroCredito> getRegistroById(@PathVariable("creditoId") Integer creditoId,
                                                           @PathVariable("adminId") Integer adminId) {
        RegistroCreditoID id = new RegistroCreditoID(creditoId, adminId);
        Optional<RegistroCredito> registro = regCreditoRepo.findById(id);
        return registro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<RegistroCredito> getAllRegistros() {
        return regCreditoRepo.findAll();
    }

    // Actualizar un registro (si la lógica de actualización aplica)
    @PutMapping("/{creditoID}/{adminID}")
    public ResponseEntity<RegistroCredito> actualizarRegistro(@PathVariable("creditoId") Integer creditoId,
                                                              @PathVariable("adminId") Integer adminId,
                                                              @RequestBody RegistroCredito registroActualizado) {
        RegistroCreditoID id = new RegistroCreditoID(creditoId, adminId);
        if (!regCreditoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registroActualizado.setId(id);
        RegistroCredito actualizado = regCreditoRepo.save(registroActualizado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un registro
    @DeleteMapping("/{creditoID}/{adminId}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable("creditoID") Integer creditoId,
                                                 @PathVariable("adminId") Integer adminId) {
        RegistroCreditoID id = new RegistroCreditoID(creditoId, adminId);
        if (!regCreditoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        regCreditoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

