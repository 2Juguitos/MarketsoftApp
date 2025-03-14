package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Entity(name = "Venta")
@Table(name = "tbl_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idventa")

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Integer idventa;

    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "Descuento")
    private BigDecimal descuento;

    @Column(name = "Fecha_Venta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Column(name = "IVA")
    private BigDecimal iva;

    @Column(name = "Total_Final")
    private BigDecimal totalFinal;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<ProductoVenta> productoVentas;

    @ManyToOne
    @JoinColumn(name = "tbl_Cliente_ID_Cliente")

    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)

    private Administrador administrador;
}
