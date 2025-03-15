package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "ProductoVenta")
@Table(name = "tbl_prodventa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ProductoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idProductoVenta;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "Subtotal")
    private BigDecimal subTotal;

    @Column(name = "Cantidad")
    private Integer cantidad;


    @ManyToOne
    @JoinColumn(name = "ID_Producto", nullable = false)
    private Producto producto;


    @ManyToOne
    @JoinColumn(name = "ID_Venta", nullable = false)
    private Venta venta;
}
