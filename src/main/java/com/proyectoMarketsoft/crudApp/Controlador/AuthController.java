package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Config.JwtUtil;
import com.proyectoMarketsoft.crudApp.DTO.request.AuthRequest;
import com.proyectoMarketsoft.crudApp.DTO.response.AuthResponse;
import com.proyectoMarketsoft.crudApp.Servicio.ClienteUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClienteUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil; // Clase para generar JWT

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println(" Intentando autenticar usuario: " + authRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            System.out.println(" Autenticación exitosa para: " + authRequest.getUsername());

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            System.out.println(" Error de autenticación: " + e.getMessage());
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

}
