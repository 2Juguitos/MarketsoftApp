package com.proyectoMarketsoft.crudApp.Modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @OneToMany(mappedBy = "Venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductoVenta> ProductoVenta;

    @ManyToOne
    @JoinColumn(name = "tbl_Cliente_ID_Cliente" )

    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)

    private Administrador administrador;
}
