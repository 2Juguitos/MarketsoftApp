package com.proyectoMarketsoft.crudApp.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import java.io.IOException;

@Component
public class JWTRequestfilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    // Inyectamos ambos servicios de UserDetailsService con sus respectivos qualifiers
    @Autowired
    @Qualifier("administradorUserDetailsService")
    private org.springframework.security.core.userdetails.UserDetailsService administradorUserDetailsService;

    @Autowired
    @Qualifier("clienteUserDetailsService")
    private org.springframework.security.core.userdetails.UserDetailsService clienteUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                System.out.println("Token expirado: " + e.getMessage());
            } catch (JwtException e) {
                System.out.println("Token inválido: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            // Primero, intenta cargar el usuario como administrador
            try {
                userDetails = administradorUserDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                // Si no se encuentra como admin, intenta cargarlo como cliente
                try {
                    userDetails = clienteUserDetailsService.loadUserByUsername(username);
                } catch (UsernameNotFoundException ex) {
                    System.out.println("Usuario no encontrado en ambos servicios: " + username);
                }
            }

            if (userDetails != null && jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}


