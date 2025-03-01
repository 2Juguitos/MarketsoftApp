package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="TelefonoCliente")
@Table(name = "tbl_tlfcliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tlfcliente")
    private Integer idTlfCliente;

    @Column(name = "tlfcliente")
    private Integer tlfCliente;

    @ManyToOne
    @JoinColumn(name = "Tbl_Cliente_ID_Cliente", nullable = false)
    private Cliente cliente;
}
