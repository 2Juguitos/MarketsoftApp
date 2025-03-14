package com.proyectoMarketsoft.crudApp.DTO.response;

public class AuthResponse {
    private String token;
    public AuthResponse(String token) { this.token = token; }
    public String getToken() { return token; }
}
