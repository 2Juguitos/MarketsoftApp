package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Proveedor;
import com.proyectoMarketsoft.crudApp.Modelo.TelefonoProveedor;
import com.proyectoMarketsoft.crudApp.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proveedores")
public class ProveedorControlador {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    // 1. Listar todos los proveedores
    @GetMapping("/listar")
    public String listarProveedores(Model model) {
        List<Proveedor> proveedores = proveedorRepositorio.findAll();
        model.addAttribute("proveedores", proveedores);
        return "proveedor/lista"; // Vista: muestra la lista de proveedores
    }

    // 2. Mostrar formulario para registrar un nuevo proveedor
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Proveedor proveedor = new Proveedor();
        model.addAttribute("proveedor", proveedor);
        return "proveedor/form"; // Vista: formulario para crear un proveedor
    }

    // 3. Guardar un nuevo proveedor (POST)
    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        proveedorRepositorio.save(proveedor); // Guardar en la base de datos
        return "redirect:/proveedores/listar"; // Redirigir a la lista
    }

    // 4. Mostrar formulario para editar un proveedor existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Optional<Proveedor> proveedorOpt = proveedorRepositorio.findById(id);
        if (proveedorOpt.isPresent()) {
            model.addAttribute("proveedor", proveedorOpt.get());
            return "proveedor/form"; // Vista: formulario prellenado para editar
        }
        return "redirect:/proveedores/listar"; // Si no encuentra el proveedor, redirige
    }

    // 5. Actualizar un proveedor existente (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarProveedor(@PathVariable("id") int id, @ModelAttribute("proveedor") Proveedor proveedorActualizado) {
        Optional<Proveedor> proveedorOpt = proveedorRepositorio.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedorExistente = proveedorOpt.get();
            // Actualizar los campos simples
            proveedorExistente.setCorreoProveedor(proveedorActualizado.getCorreoProveedor());
            proveedorExistente.setEmpresaProv(proveedorActualizado.getEmpresaProv());
            proveedorExistente.setNombreProv(proveedorActualizado.getNombreProv());
            proveedorExistente.setApellidoProv(proveedorActualizado.getApellidoProv());
            proveedorExistente.setAdministrador(proveedorActualizado.getAdministrador());

            // Actualización de la colección 'telefonos'
            List<TelefonoProveedor> telefonosActuales = proveedorExistente.getTelefonos();
            telefonosActuales.clear(); // Limpia la lista actual
            if (proveedorActualizado.getTelefonos() != null) {
                telefonosActuales.addAll(proveedorActualizado.getTelefonos());
            }

            proveedorRepositorio.save(proveedorExistente); // Guardar en la base de datos
        }
        return "redirect:/proveedores/listar"; // Redirigir a la lista
    }


    // 6. Eliminar un proveedor por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") int id) {
        proveedorRepositorio.deleteById(id); // Eliminar de la base de datos
        return "redirect:/proveedores/listar"; // Redirigir a la lista
    }
}