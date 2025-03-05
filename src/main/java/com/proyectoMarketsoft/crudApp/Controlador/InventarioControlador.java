package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Inventario;
import com.proyectoMarketsoft.crudApp.Servicio.InventarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventario")
public class InventarioControlador {

    private final InventarioServicio inventarioServicio;

    @Autowired
    public InventarioControlador(InventarioServicio inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }

    // Mostrar la lista de inventarios
    @GetMapping("/listar")
    public String listarInventarios(Model model) {
        List<Inventario> lista = inventarioServicio.listarTodos();
        model.addAttribute("inventarios", lista);
        return "inventario/lista"; // se buscará en /WEB-INF/views/inventario/lista.jsp
    }

    // Mostrar formulario para crear un nuevo inventario
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("inventario", new Inventario());
        return "inventario/form"; // se buscará en /WEB-INF/views/inventario/form.jsp
    }

    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
        // Si no se envía un administrador, asigna el valor por defecto "1"
        if (inventario.getAdministrador() == null || inventario.getAdministrador().getIdAdmin() == null) {
            var admin = new com.proyectoMarketsoft.crudApp.Modelo.Administrador();
            admin.setIdAdmin(1);
            inventario.setAdministrador(admin);
        }
        inventarioServicio.guardar(inventario);
        return "redirect:" + "/inventario/listar";
    }

    // Mostrar formulario para editar un inventario existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Inventario> inventarioOpt = inventarioServicio.buscarPorId(id);
        if (inventarioOpt.isPresent()) {
            model.addAttribute("inventario", inventarioOpt.get());
            return "inventario/form";
        } else {
            return "redirect:" + "/inventario/listar";
        }
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarInventario(@PathVariable Integer id, @ModelAttribute("inventario") Inventario inventarioActualizado) {
        // Si el administrador no se envía, asigna por defecto "1"
        if (inventarioActualizado.getAdministrador() == null || inventarioActualizado.getAdministrador().getIdAdmin() == null) {
            var admin = new com.proyectoMarketsoft.crudApp.Modelo.Administrador();
            admin.setIdAdmin(1);
            inventarioActualizado.setAdministrador(admin);
        }
        inventarioServicio.actualizar(id, inventarioActualizado);
        return "redirect:" + "/inventario/listar";
    }

    // Eliminar un inventario (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarInventario(@PathVariable Integer id) {
        inventarioServicio.eliminar(id);
        return "redirect:" + "/inventario/listar";
    }
}
