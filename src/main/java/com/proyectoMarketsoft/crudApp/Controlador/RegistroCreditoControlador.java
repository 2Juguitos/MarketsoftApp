package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.RegistroCredito;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCreditoID;
import com.proyectoMarketsoft.crudApp.Repositorio.RegistroCreditoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/regcredito")
public class RegistroCreditoControlador {

    @Autowired
    private RegistroCreditoRepositorio regCreditoRepo;

    // Listar todos los registros de crédito
    @GetMapping("/lista")
    public String listarRegistroCreditos(Model model) {
        List<RegistroCredito> registros = regCreditoRepo.findAll();
        model.addAttribute("registros", registros);
        return "registrocredito/lista"; // Asegúrate de tener esta vista
    }

    // Mostrar formulario para crear un nuevo registro de crédito
    @GetMapping("/form")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("registroCredito", new RegistroCredito());
        return "registrocredito/form"; // Asegúrate de tener esta vista
    }

    // Guardar un registro de crédito
    @PostMapping("/guardar")
    public String guardarRegistroCredito(@ModelAttribute RegistroCredito registroCredito, Model model) {
        try {
            // Guardar registro en la base de datos
            regCreditoRepo.save(registroCredito);
            return "redirect:/regcredito/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el registro de crédito. Verifique los datos ingresados.");
            return "registrocredito/form";
        }
    }

    // Mostrar un registro de crédito específico
    @GetMapping("/ver/{creditoID}/{adminID}")
    public String verRegistroCredito(@PathVariable("creditoID") Integer creditoId,
                                     @PathVariable("adminID") Integer adminId,
                                     Model model) {
        RegistroCreditoID id = new RegistroCreditoID(creditoId, adminId);
        Optional<RegistroCredito> registro = regCreditoRepo.findById(id);

        if (registro.isPresent()) {
            model.addAttribute("registroCredito", registro.get());
            return "registrocredito/ver"; // Asegúrate de crear esta vista si es necesaria
        } else {
            model.addAttribute("error", "Registro de crédito no encontrado.");
            return "redirect:/regcredito/lista";
        }
    }

    // Eliminar un registro de crédito por su ID compuesto
    @GetMapping("/eliminar/{creditoID}/{adminID}")
    public String eliminarRegistroCredito(@PathVariable("creditoID") Integer creditoId,
                                          @PathVariable("adminID") Integer adminId) {
        RegistroCreditoID id = new RegistroCreditoID(creditoId, adminId);
        if (regCreditoRepo.existsById(id)) {
            regCreditoRepo.deleteById(id);
        }
        return "redirect:/regcredito/lista";
    }
}