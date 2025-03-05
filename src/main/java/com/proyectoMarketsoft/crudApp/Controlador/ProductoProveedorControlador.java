package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedor;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/productoproveedor")
public class ProductoProveedorControlador {

    @Autowired
    private ProductoProveedorRepositorio productoProveedorRepositorio;

    // Listar todos los registros
    @GetMapping("/lista")
    public String listarProductoProveedor(Model model) {
        model.addAttribute("productoProveedores", productoProveedorRepositorio.findAll());
        return "productoproveedor/lista"; // Asegúrate de tener esta vista
    }

    // Mostrar formulario para crear un nuevo ProductoProveedor
    @GetMapping("/form")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("productoProveedor", new ProductoProveedor());
        return "productoproveedor/form"; // Asegúrate de tener esta vista
    }

    // Guardar un ProductoProveedor
    @PostMapping("/guardar")
    public String guardarProductoProveedor(@ModelAttribute ProductoProveedor productoProveedor) {
        productoProveedorRepositorio.save(productoProveedor); // Guardar en la base de datos
        return "redirect:/productoproveedor/lista";
    }

    // Eliminar un ProductoProveedor por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarProductoProveedor(@PathVariable("id") Integer id) {
        Optional<ProductoProveedor> productoProveedor = productoProveedorRepositorio.findById(id);
        if (productoProveedor.isPresent()) {
            productoProveedorRepositorio.deleteById(id); // Eliminar si existe
        }
        return "redirect:/productoproveedor/lista";
    }
}