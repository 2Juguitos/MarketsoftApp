package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")

public class AdministradorControlador {
    @Autowired
    private AdministradorRepositorio AdministradorRepositorio;

    @GetMapping
    public List<Administrador> listarAdministradores(){
        return AdministradorRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Administrador obtenerAdministrador(@PathVariable Integer id) {
        return AdministradorRepositorio.findById(id).orElse(null);

    }

    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return AdministradorRepositorio.save(administrador);}

    @PutMapping("/{id}")
    public Administrador actualizarAdministrador (@PathVariable Integer id, @RequestBody Administrador adminActualizado) {
        Administrador adminExistente = AdministradorRepositorio.findById(id).orElse(null);
        if (adminExistente != null) {
            adminExistente.setNombreAdmin(adminActualizado.getNombreAdmin());
            return AdministradorRepositorio.save(adminExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarAdministrador(@PathVariable Integer id) {
        AdministradorRepositorio.deleteById(id);
    }

}

