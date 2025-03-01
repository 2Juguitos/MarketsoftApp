package com.proyectoMarketsoft.crudApp.Modelo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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
    private Integer id;


    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "Cantidad")
    private Integer cantidad;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "ID_Producto", nullable = false)
    private Producto Producto;

    // Relación con Venta
    @ManyToOne
    @JoinColumn(name = "ID_Venta", nullable = false)
    @JsonBackReference
    private Venta Venta;

}