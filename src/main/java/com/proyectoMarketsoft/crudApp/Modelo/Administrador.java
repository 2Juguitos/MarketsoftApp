package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Administrador")
@Table(name = "tbl_administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAdmin")
public class Administrador {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_Admin")
        private Integer idAdmin;

        @Column(name = "Nombre_Admin", nullable = false, length = 45)
        private String nombreAdmin;


        @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Producto> productos;

        @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Cliente> clientes;


        @OneToMany(mappedBy = "administrador")
        private List<Inventario> inventarios;
}
