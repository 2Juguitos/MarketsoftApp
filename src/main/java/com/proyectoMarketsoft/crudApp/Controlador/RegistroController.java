package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class RegistroController {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro de clientes
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente cliente) {
        System.out.println("Recibiendo solicitud de registro (cliente): " + cliente.getUsername());

        if (cliente.getAdministrador() == null) {
            // Se asigna el administrador por defecto (ID 1)
            Administrador admin = administradorRepositorio.findById(1)
                    .orElseThrow(() -> new RuntimeException("No se encontró el administrador por defecto"));
            cliente.setAdministrador(admin);
        }

        // Encriptar la contraseña antes de guardar
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        Cliente savedCliente = clienteRepository.save(cliente);

        System.out.println("Cliente registrado exitosamente: " + savedCliente.getIdCliente());
        return ResponseEntity.ok(savedCliente);
    }

    // Registro de administradores
    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Administrador admin) {
        System.out.println("Recibiendo solicitud de registro de administrador: " + admin.getUsername());

        // Verificar si ya existe un administrador con ese username
        if (administradorRepositorio.findByUsername(admin.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El administrador ya existe.");
        }

        // Encriptar la contraseña y asignar rol por defecto si no se especifica
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        if (admin.getRol() == null || admin.getRol().isEmpty()) {
            admin.setRol("ROLE_ADMIN");
        }

        Administrador savedAdmin = administradorRepositorio.save(admin);
        System.out.println("Administrador registrado exitosamente: " + savedAdmin.getIdAdmin());
        return ResponseEntity.ok(savedAdmin);
    }
}
