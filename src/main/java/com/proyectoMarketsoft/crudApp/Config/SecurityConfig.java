package com.proyectoMarketsoft.crudApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTRequestfilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF para Postman
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Permitir TODO sin autenticación
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    // @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider authProvider) throws Exception {
//     http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(authorize -> authorize
//                     .requestMatchers("/api/auth/**").permitAll()  // Login y registro públicos
//                     .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll() // Visualización de productos es pública
//                     .requestMatchers(HttpMethod.GET, "/api/ventas/**").permitAll() // Visualización de ventas es pública
//                     .requestMatchers(HttpMethod.POST, "/api/ventas/**", "/api/inventarios/**", "/api/proveedores/**").authenticated() // Requiere autenticación para creación
//                     .requestMatchers(HttpMethod.PUT, "/api/ventas/**", "/api/inventarios/**", "/api/proveedores/**").authenticated() // Requiere autenticación para modificaciones
//                     .requestMatchers(HttpMethod.DELETE, "/api/ventas/**", "/api/inventarios/**", "/api/proveedores/**").authenticated() // Requiere autenticación para eliminación
//                     .anyRequest().permitAll() // Permite acceso sin restricciones a cualquier otra solicitud
//             )
//             .authenticationProvider(authProvider)
//             .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

//     return http.build();
// }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

