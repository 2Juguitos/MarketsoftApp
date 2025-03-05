package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import com.proyectoMarketsoft.crudApp.Servicio.ServicioVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/venta")
public class VentaControlador {

    @Autowired
    private ServicioVenta ventaServicio;

    // Listar ventas
    @GetMapping("/listar")
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaServicio.getAllVentas();
        model.addAttribute("ventas", ventas);
        return "venta/lista";  // Se buscará en /WEB-INF/views/venta/lista.jsp
    }

    // Mostrar formulario para crear una nueva venta
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Venta venta = new Venta();
        // Inicializamos la lista de ProductoVenta con un elemento vacío para facilitar el form
        venta.setProductoVenta(new java.util.ArrayList<>());
        venta.getProductoVenta().add(new com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta());
        model.addAttribute("venta", venta);
        return "venta/form"; // Se buscará en /WEB-INF/views/venta/form.jsp
    }

    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        if (venta.getCliente() != null && venta.getCliente().getIdCliente() == null) {
            // Si el cliente es nuevo y no debe ser persistido en cascada, asigna null
            venta.setCliente(null);
        }
        ventaServicio.crearVenta(venta);
        return "redirect:/venta/listar";
    }

    // Mostrar formulario para editar una venta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Venta> ventaOpt = ventaServicio.getVentaById(id);
        if (ventaOpt.isPresent()) {
            model.addAttribute("venta", ventaOpt.get());
            return "venta/form";
        }
        return "redirect:/venta/listar";
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarVenta(@PathVariable Integer id, @ModelAttribute("venta") Venta venta) {
        ventaServicio.actualizarVenta(id, venta);
        return "redirect:/venta/listar";
    }

    // Eliminar mi venta
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id) {
        ventaServicio.eliminarVenta(id);
        return "redirect:/venta/listar";
    }
}
