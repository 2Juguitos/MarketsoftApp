package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteControlador(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    // Mostrar la lista de clientes
    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente/lista";  // Busca el JSP en /WEB-INF/views/cliente/lista.jsp
    }

    // Mostrar formulario para crear un nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form"; // Busca /WEB-INF/views/cliente/form.jsp
    }

    // Procesar el formulario de creación (POST)
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteRepositorio.save(cliente);
        return "redirect:/clientes/listar";
    }

    // Mostrar formulario para editar un cliente existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Cliente> clienteOpt = clienteRepositorio.findById(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            return "cliente/form";
        }
        return "redirect:/clientes/listar";
    }

    // Procesar la actualización (POST)
    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute("cliente") Cliente clienteActualizado) {
        Optional<Cliente> clienteOpt = clienteRepositorio.findById(id);
        if (clienteOpt.isPresent()){
            Cliente clienteExistente = clienteOpt.get();
            clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
            clienteExistente.setApellidoCliente(clienteActualizado.getApellidoCliente());
            clienteExistente.setAdministrador(clienteActualizado.getAdministrador());
            clienteRepositorio.save(clienteExistente);
        }
        return "redirect:/clientes/listar";
    }

    // Eliminar un cliente (GET)
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        if (clienteRepositorio.existsById(id)) {
            clienteRepositorio.deleteById(id);
        }
        return "redirect:/clientes/listar";
    }
}
