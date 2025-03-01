package com.proyectoMarketsoft.crudApp.Modelo;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Data
@Entity(name = "Credito")
@Table(name = "tbl_credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Credito")
    private Integer idCredito;

    @Column(name = "Cupo_Credito")
    private BigDecimal cupoCredito;


    @Column(name = "SaldoPend_Credito", insertable = false, updatable = false)
    private BigDecimal saldoPendCredito;

    @ManyToOne
    @JoinColumn(name = "Tbl_Cliente_ID_Cliente", nullable = false)
    private Cliente cliente;


    @Transient
    private Integer idAdministrador;


}
