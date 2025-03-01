package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Producto")
@Table(name = "tbl_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private Integer idProducto;

    @Column(name = "Marca_Prod", nullable = false)
    private String marcaProd;

    @Column(name = "Nombre_Prod", nullable = false)
    private String nombreProd;


    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Inventario> inventarios;

    @ManyToOne
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "tbl_Categoria_idtbl_Categoria", nullable = false)
    private Categoria categoria;
}
