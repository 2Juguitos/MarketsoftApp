package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoVentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productoventa")
public class ProductoVentaControlador {

    @Autowired
    private ProductoVentaRepositorio prodVentaRepositorio;

    // Listar todos los registros de ProductoVenta
    @GetMapping("/listar")
    public String listarProductoVenta(Model model) {
        List<ProductoVenta> lista = prodVentaRepositorio.findAll();
        model.addAttribute("prodVentas", lista);
        return "productoventa/lista";  // Mapea a /WEB-INF/views/productoventa/lista.jsp
    }

    // Mostrar formulario para crear un nuevo registro de ProductoVenta
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("prodVenta", new ProductoVenta());
        return "productoventa/form";  // Mapea a /WEB-INF/views/productoventa/form.jsp
    }

    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarProductoVenta(@ModelAttribute("prodVenta") ProductoVenta prodVenta) {
        prodVentaRepositorio.save(prodVenta);
        return "redirect:/productoventa/listar";
    }

    // Mostrar formulario para editar un registro existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<ProductoVenta> prodVentaOpt = prodVentaRepositorio.findById(id);
        if (prodVentaOpt.isPresent()) {
            model.addAttribute("prodVenta", prodVentaOpt.get());
            return "productoventa/form";
        }
        return "redirect:/productoventa/listar";
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarProductoVenta(@PathVariable Integer id, @ModelAttribute("prodVenta") ProductoVenta prodVentaActualizado) {
        Optional<ProductoVenta> prodVentaOpt = prodVentaRepositorio.findById(id);
        if (prodVentaOpt.isPresent()) {
            ProductoVenta prodVentaExistente = prodVentaOpt.get();
            prodVentaExistente.setPrecioUnitario(prodVentaActualizado.getPrecioUnitario());
            prodVentaExistente.setCantidad(prodVentaActualizado.getCantidad());
            prodVentaExistente.setProducto(prodVentaActualizado.getProducto());
            prodVentaExistente.setVenta(prodVentaActualizado.getVenta());
            prodVentaRepositorio.save(prodVentaExistente);
        }
        return "redirect:/productoventa/listar";
    }

    // Eliminar un registro (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarProductoVenta(@PathVariable Integer id) {
        prodVentaRepositorio.deleteById(id);
        return "redirect:/productoventa/listar";
    }
}
