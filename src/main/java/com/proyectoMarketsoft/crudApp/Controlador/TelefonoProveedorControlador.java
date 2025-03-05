package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.TelefonoProveedor;
import com.proyectoMarketsoft.crudApp.Modelo.Proveedor;
import com.proyectoMarketsoft.crudApp.Repositorio.TelefonoProveedorRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/telefonosprov")
public class TelefonoProveedorControlador {

    @Autowired
    private TelefonoProveedorRepositorio telefonoProveedorRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    // Listar todos los teléfonos de proveedor
    @GetMapping("/listar")
    public String listarTelefonos(Model model) {
        List<TelefonoProveedor> telefonos = telefonoProveedorRepositorio.findAll();
        model.addAttribute("telefonos", telefonos);
        return "tlfprov/lista"; // Ubicado en /WEB-INF/views/tlfprov/lista.jsp
    }

    // Mostrar formulario para crear un nuevo teléfono de proveedor
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("telefono", new TelefonoProveedor());
        return "tlfprov/form"; // Ubicado en /WEB-INF/views/tlfprov/form.jsp
    }

    // Procesar el formulario para guardar un nuevo teléfono
    @PostMapping("/guardar")
    public String guardarTelefono(@ModelAttribute("telefono") TelefonoProveedor telefono) {
        // Validar que se haya enviado un proveedor y que su ID no sea 0
        if (telefono.getProveedor() != null && telefono.getProveedor().getIdProveedor() != 0) {
            Optional<Proveedor> proveedorOpt = proveedorRepositorio.findById(telefono.getProveedor().getIdProveedor());
            if (proveedorOpt.isPresent()) {
                telefono.setProveedor(proveedorOpt.get());
            } else {
                // Redirige con error si no existe el proveedor
                return "redirect:/telefonosprov/nuevo?error=ProveedorNoExiste";
            }
        } else {
            // Si no se asignó un proveedor válido, se redirige con error
            return "redirect:/telefonosprov/nuevo?error=ProveedorNoAsignado";
        }
        telefonoProveedorRepositorio.save(telefono);
        return "redirect:/telefonosprov/listar";
    }

    // Mostrar formulario para editar un teléfono existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Optional<TelefonoProveedor> telefonoOpt = telefonoProveedorRepositorio.findById(id);
        if (telefonoOpt.isPresent()) {
            model.addAttribute("telefono", telefonoOpt.get());
            return "tlfprov/form";
        }
        return "redirect:/telefonosprov/listar";
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarTelefono(@PathVariable("id") Integer id,
                                     @ModelAttribute("telefono") TelefonoProveedor telefonoActualizado) {
        Optional<TelefonoProveedor> telefonoOpt = telefonoProveedorRepositorio.findById(id);
        if (telefonoOpt.isPresent()) {
            TelefonoProveedor telefonoExistente = telefonoOpt.get();
            telefonoExistente.setTelProv(telefonoActualizado.getTelProv());
            if (telefonoActualizado.getProveedor() != null && telefonoActualizado.getProveedor().getIdProveedor() != 0) {
                Optional<Proveedor> proveedorOpt = proveedorRepositorio.findById(telefonoActualizado.getProveedor().getIdProveedor());
                proveedorOpt.ifPresent(telefonoExistente::setProveedor);
            }
            telefonoProveedorRepositorio.save(telefonoExistente);
        }
        return "redirect:/telefonosprov/listar";
    }

    // Eliminar un teléfono (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarTelefono(@PathVariable("id") Integer id) {
        if (telefonoProveedorRepositorio.existsById(id)) {
            telefonoProveedorRepositorio.deleteById(id);
        }
        return "redirect:/telefonosprov/listar";
    }
}
