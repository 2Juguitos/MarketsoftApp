package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "cliente")
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private Integer idCliente;

    @Column(name = "Nombre_Cliente", length = 45)
    private String nombreCliente;

    @Column(name = "Apellido_Cliente", length = 45)
    private String apellidoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefonoCliente> telefonos;

    @ManyToOne
    @JoinColumn(name = "Administrador_ID_Admin", nullable = false)
    private Administrador administrador;
}
