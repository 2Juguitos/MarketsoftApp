package com.proyectoMarketsoft.crudApp.Servicio;



import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class AdministradorUserDetails implements UserDetails {

    private final Administrador administrador;

    public AdministradorUserDetails(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve la autoridad basándose en el campo rol
        return Collections.singletonList(new SimpleGrantedAuthority(administrador.getRol()));
    }

    @Override
    public String getPassword() {
        return administrador.getPassword();
    }

    @Override
    public String getUsername() {
        return administrador.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
