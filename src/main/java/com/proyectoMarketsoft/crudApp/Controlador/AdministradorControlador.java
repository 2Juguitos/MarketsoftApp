package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administradores")
public class AdministradorControlador {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    // Mostrar lista de administradores en una página JSP
    @GetMapping("/listar")
    public String listarAdministradores(Model model) {
        List<Administrador> lista = administradorRepositorio.findAll();
        model.addAttribute("administradores", lista);
        return "listaAdministradores"; // Esto corresponde a /WEB-INF/views/listaAdministradores.jsp
    }

    // Mostrar formulario para crear un nuevo administrador
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "formAdministrador"; // Corresponde a /WEB-INF/views/formAdministrador.jsp
    }

    // Procesar el formulario de creación de administrador (método POST)
    @PostMapping("/guardar")
    public String guardarAdministrador(@ModelAttribute("administrador") Administrador administrador) {
        administradorRepositorio.save(administrador);
        return "redirect:/administradores/listar";
    }

    // Mostrar formulario para editar un administrador existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Administrador admin = administradorRepositorio.findById(id).orElse(null);
        model.addAttribute("administrador", admin);
        return "formAdministrador"; // Reutilizamos el mismo formulario para crear/editar
    }

    // Procesar la actualización (método POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarAdministrador(@PathVariable Integer id, @ModelAttribute("administrador") Administrador adminActualizado) {
        Administrador adminExistente = administradorRepositorio.findById(id).orElse(null);
        if (adminExistente != null) {
            adminExistente.setNombreAdmin(adminActualizado.getNombreAdmin());
            administradorRepositorio.save(adminExistente);
        }
        return "redirect:/administradores/listar";
    }

    // Eliminar un administrador (método GET para eliminar, que redirige a la lista)
    @GetMapping("/eliminar/{id}")
    public String eliminarAdministrador(@PathVariable Integer id) {
        administradorRepositorio.deleteById(id);
        return "redirect:/administradores/listar";
    }
}

