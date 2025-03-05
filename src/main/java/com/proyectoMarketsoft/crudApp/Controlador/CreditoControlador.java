package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Credito;
import com.proyectoMarketsoft.crudApp.Servicio.CreditoServicio;
import com.proyectoMarketsoft.crudApp.Repositorio.CreditoRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/credito")
public class CreditoControlador {

    @Autowired
    private CreditoRepositorio creditoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CreditoServicio creditoServicio;

    // Listar todos los créditos
    @GetMapping("/lista")
    public String listarCreditos(Model model) {
        List<Credito> creditos = creditoRepositorio.findAll();
        model.addAttribute("creditos", creditos);
        return "credito/lista"; // Vista lista.jsp
    }

    // Mostrar formulario para crear un nuevo crédito
    @GetMapping("/form")
    public String mostrarFormularioCredito(Model model) {
        model.addAttribute("clientes", clienteRepositorio.findAll()); // Lista de clientes para seleccionar
        model.addAttribute("credito", new Credito());
        return "credito/form"; // Vista form.jsp
    }

    // Guardar un nuevo crédito
    @PostMapping("/guardar")
    public String guardarCredito(@ModelAttribute Credito credito, Model model) {
        try {
            // Crear el crédito y su correspondiente registro
            creditoServicio.crearCreditoYRegistro(credito);
            return "redirect:/credito/lista";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("clientes", clienteRepositorio.findAll());
            return "credito/form";
        }
    }

    // Mostrar un crédito por ID
    @GetMapping("/ver/{id}")
    public String verCredito(@PathVariable Integer id, Model model) {
        Optional<Credito> credito = creditoRepositorio.findById(id);
        if (credito.isPresent()) {
            model.addAttribute("credito", credito.get());
            return "credito/ver"; // Vista ver.jsp si es necesaria
        } else {
            model.addAttribute("error", "Crédito no encontrado.");
            return "redirect:/credito/lista";
        }
    }

    // Mostrar el formulario para editar un crédito existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Credito> credito = creditoRepositorio.findById(id);
        if (credito.isPresent()) {
            model.addAttribute("credito", credito.get());
            model.addAttribute("clientes", clienteRepositorio.findAll());
            return "credito/form"; // Reutiliza el formulario para edición
        } else {
            model.addAttribute("error", "Crédito no encontrado.");
            return "redirect:/credito/lista";
        }
    }

    // Eliminar un crédito por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarCredito(@PathVariable Integer id) {
        if (creditoRepositorio.existsById(id)) {
            creditoRepositorio.deleteById(id);
        }
        return "redirect:/credito/lista";
    }
}