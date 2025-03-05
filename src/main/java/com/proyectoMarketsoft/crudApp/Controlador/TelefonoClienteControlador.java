package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.TelefonoCliente;
import com.proyectoMarketsoft.crudApp.Repositorio.TelefonoClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tlfcliente")
public class TelefonoClienteControlador {

    @Autowired
    private TelefonoClienteRepositorio tlfClienteRepo;

    // Mostrar la lista de teléfonos de cliente
    @GetMapping("/listar")
    public String listarTelefonos(Model model) {
        List<TelefonoCliente> telefonos = tlfClienteRepo.findAll();
        model.addAttribute("telefonos", telefonos);
        return "tlfcliente/lista";  // Ubicado en /WEB-INF/views/tlfcliente/lista.jsp
    }

    // Mostrar el formulario para agregar un nuevo teléfono
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("telefono", new TelefonoCliente());
        return "tlfcliente/form";  // Ubicado en /WEB-INF/views/tlfcliente/form.jsp
    }

    // Procesar el formulario para guardar un nuevo teléfono
    @PostMapping("/guardar")
    public String guardarTelefono(@ModelAttribute("telefono") TelefonoCliente telefono) {
        tlfClienteRepo.save(telefono);
        return "redirect:/tlfcliente/listar";
    }

    // Mostrar formulario para editar un teléfono existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Optional<TelefonoCliente> telefonoOpt = tlfClienteRepo.findById(id);
        if (telefonoOpt.isPresent()) {
            model.addAttribute("telefono", telefonoOpt.get());
            return "tlfcliente/form";
        }
        return "redirect:/tlfcliente/listar";
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarTelefono(@PathVariable("id") Integer id, @ModelAttribute("telefono") TelefonoCliente telefonoActualizado) {
        Optional<TelefonoCliente> telefonoOpt = tlfClienteRepo.findById(id);
        if (telefonoOpt.isPresent()) {
            TelefonoCliente telefonoExistente = telefonoOpt.get();
            telefonoExistente.setTlfCliente(telefonoActualizado.getTlfCliente());
            // Se asume que el objeto cliente se envía correctamente (campo cliente.idCliente)
            telefonoExistente.setCliente(telefonoActualizado.getCliente());
            tlfClienteRepo.save(telefonoExistente);
        }
        return "redirect:/tlfcliente/listar";
    }

    // Eliminar un teléfono (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarTelefono(@PathVariable("id") Integer id) {
        if (tlfClienteRepo.existsById(id)) {
            tlfClienteRepo.deleteById(id);
        }
        return "redirect:/tlfcliente/listar";
    }
}
