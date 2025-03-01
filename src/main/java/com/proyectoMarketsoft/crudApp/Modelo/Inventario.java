package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "inventario")
@Table(name = "tbl_inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Inventario")
    private Integer id;

    @Column(name = "Precio_Inv", nullable = false)
    private BigDecimal precio;

    @Column(name = "Stock_Inventario")
    private Integer stock;

    @Column(name = "Fecha_Registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "Producto_Disponible")
    private Boolean productoDisponible;

    @ManyToOne
    @JoinColumn(name = "Tbl_Producto_ID_Producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)
    private Administrador administrador;
}
