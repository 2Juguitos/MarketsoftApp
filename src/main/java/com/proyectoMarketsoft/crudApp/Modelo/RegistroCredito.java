package com.proyectoMarketsoft.crudApp.Modelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "RegistroCredito")
@Table(name = "tbl_regcredito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCredito {

    @EmbeddedId
    private RegistroCreditoID id;

    @ManyToOne
    @MapsId("creditoIdCredito")
    @JoinColumn(name = "Credito_ID_Credito", referencedColumnName = "ID_Credito", insertable = false, updatable = false)
    private Credito credito;

    @ManyToOne
    @MapsId("administradorIdAdmin")
    @JoinColumn(name = "Administrador_ID_Admin", referencedColumnName = "ID_Admin", insertable = false, updatable = false)
    private Administrador administrador;
}

