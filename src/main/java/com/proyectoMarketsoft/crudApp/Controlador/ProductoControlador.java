package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    // Listar todos los productos (incluyendo el nombre de la categoría)
    @GetMapping("/listar")
    public String listarProductos(Model model) {
        List<Producto> productos = productoRepositorio.findAll();
        model.addAttribute("productos", productos);
        return "producto/lista"; // buscará /WEB-INF/views/producto/lista.jsp
    }

    // Mostrar formulario para crear un nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/form"; // buscará /WEB-INF/views/producto/form.jsp
    }





    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoRepositorio.save(producto);
        return "redirect:/productos/listar";
    }

    // Mostrar formulario para editar un producto existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Producto producto = productoRepositorio.findById(id).orElse(null);
        model.addAttribute("producto", producto);
        return "producto/form"; // reutiliza el mismo formulario
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Integer id, @ModelAttribute("producto") Producto productoActualizado) {
        if (productoRepositorio.existsById(id)) {
            productoActualizado.setIdProducto(id);
            productoRepositorio.save(productoActualizado);
        }
        return "redirect:/productos/listar";
    }

    // Eliminar un producto (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
        }
        return "redirect:/productos/listar";
    }
}
