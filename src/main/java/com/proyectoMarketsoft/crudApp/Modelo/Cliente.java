package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@Entity(name = "cliente")
@Table(name = "tbl_cliente")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private Integer idCliente;

    @Column(name = "Nombre_Cliente", length = 45)
    private String nombreCliente;

    @Column(name = "Apellido_Cliente", length = 45)
    private String apellidoCliente;

    // Nuevos campos para autenticación
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    //  roles
    @Column(name = "rol", nullable = false, length = 20)
    private String rol = "CLIENTE"; // Valor por defecto

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    @ManyToOne
    @JoinColumn(name = "Administrador_ID_Admin", nullable = false)
    private Administrador administrador;
}
