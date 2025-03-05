package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Categoria;
import com.proyectoMarketsoft.crudApp.Repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    // Mostrar lista de categorías en una página JSP
    @GetMapping("/listar")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        model.addAttribute("categorias", categorias);
        return "categoria/lista"; // Busca /WEB-INF/views/categoria/lista.jsp
    }

    // Mostrar formulario para crear una nueva categoría
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form"; // Busca /WEB-INF/views/categoria/form.jsp
    }

    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaRepositorio.save(categoria);
        return "redirect:/categorias/listar";
    }

    // Mostrar formulario para editar una categoría existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepositorio.findById(id).orElse(null);
        model.addAttribute("categoria", categoria);
        return "categoria/form"; // Reutiliza el mismo formulario
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarCategoria(@PathVariable Integer id, @ModelAttribute("categoria") Categoria categoriaActualizada) {
        Categoria categoriaExistente = categoriaRepositorio.findById(id).orElse(null);
        if (categoriaExistente != null) {
            categoriaExistente.setNombreCategoria(categoriaActualizada.getNombreCategoria());
            categoriaExistente.setFechaRegistroCategoria(categoriaActualizada.getFechaRegistroCategoria());
            categoriaRepositorio.save(categoriaExistente);
        }
        return "redirect:/categorias/listar";
    }

    // Eliminar una categoría (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        if (categoriaRepositorio.existsById(id)) {
            categoriaRepositorio.deleteById(id);
        }
        return "redirect:/categorias/listar";
    }
}
