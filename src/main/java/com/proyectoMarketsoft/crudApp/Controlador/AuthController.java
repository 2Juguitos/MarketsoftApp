package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Config.JwtUtil;
import com.proyectoMarketsoft.crudApp.DTO.request.AuthRequest;
import com.proyectoMarketsoft.crudApp.DTO.response.AuthResponse;
import com.proyectoMarketsoft.crudApp.Servicio.AdministradorUserDetailsService;
import com.proyectoMarketsoft.crudApp.Servicio.ClienteUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Servicio para cargar detalles de clientes
    @Autowired
    private ClienteUserDetailsService userDetailsService;

    // Servicio para cargar detalles de administradores
    @Autowired
    private AdministradorUserDetailsService adminUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint de login para clientes
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Usamos authenticationManager.authenticate() para clientes

            // Aquí se usa el mecanismo configurado en SecurityConfig.
            // Si ya se autentica correctamente, se genera el token.
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("Contraseña incorrecta para cliente");
            }
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            System.out.println("Error de autenticación para cliente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    // Endpoint de login para administradores
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Intentando autenticar admin: " + authRequest.getUsername());
            // Usamos el servicio específico para administradores
            UserDetails adminDetails = adminUserDetailsService.loadUserByUsername(authRequest.getUsername());
            // Validamos la contraseña manualmente con el PasswordEncoder
            if (!passwordEncoder.matches(authRequest.getPassword(), adminDetails.getPassword())) {
                throw new BadCredentialsException("Contraseña incorrecta para administrador");
            }
            System.out.println("Autenticación exitosa para admin: " + authRequest.getUsername());
            String token = jwtUtil.generateToken(adminDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            System.out.println("Error de autenticación para admin: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}

