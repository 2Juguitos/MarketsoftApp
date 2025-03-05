package com.proyectoMarketsoft.crudApp.Modelo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;


@Entity(name = "Venta")
@Table(name = "tbl_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Integer idVenta;

    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "Descuento")
    private BigDecimal descuento;

    @Column(name = "Fecha_Venta", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    // Campo para mostrar IVA generado en la BD
    @Column(name = "IVA", insertable = false, updatable = false)
    private BigDecimal iva;

    // Campo para mostrar Total_Final generado en la BD
    @Column(name = "Total_Final", insertable = false, updatable = false)
    private BigDecimal totalFinal;

    @OneToMany(mappedBy = "Venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductoVenta> ProductoVenta;

    @ManyToOne
    @JoinColumn(name = "tbl_Cliente_ID_Cliente", nullable = true)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)
    private Administrador administrador;
}
