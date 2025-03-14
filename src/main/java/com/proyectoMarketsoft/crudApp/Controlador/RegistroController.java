package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin ("*")
public class RegistroController {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente cliente) {
        System.out.println("Recibiendo solicitud de registro: " + cliente.getUsername());

        if(cliente.getAdministrador() == null) {
            Administrador admin = administradorRepositorio.findById(1)
                    .orElseThrow(() -> new RuntimeException("No se encontró el administrador por defecto"));
            cliente.setAdministrador(admin);
        }

        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        Cliente savedCliente = clienteRepository.save(cliente);

        System.out.println("Cliente registrado exitosamente: " + savedCliente.getIdCliente());
        return ResponseEntity.ok(savedCliente);
    }


}
