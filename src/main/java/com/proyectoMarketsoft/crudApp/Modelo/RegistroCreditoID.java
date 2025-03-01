package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCreditoID implements Serializable {

    @Column(name = "Credito_ID_Credito")
    private Integer creditoIdCredito;

    @Column(name = "Administrador_ID_Admin")
    private Integer administradorIdAdmin;
}

