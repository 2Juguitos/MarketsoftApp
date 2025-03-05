package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "proveedor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tbl_proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Proveedor")
    private Integer idProveedor;

    @Column(name = "Correo_Proveedor")
    private String correoProveedor;

    @Column(name = "Empresa_Prov")
    private String empresaProv;

    @Column(name = "Nombre_Prov")
    private String nombreProv;

    @Column(name = "Apellido_Prov")
    private String apellidoProv;

    /** Relación con Administrador **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", referencedColumnName = "ID_Admin", nullable = false)
    @JsonIgnore
    private Administrador administrador;

    /** Relación con Teléfonos **/
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TelefonoProveedor> telefonos = new ArrayList<>();

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", correoProveedor='" + correoProveedor + '\'' +
                ", empresaProv='" + empresaProv + '\'' +
                ", nombreProv='" + nombreProv + '\'' +
                ", apellidoProv='" + apellidoProv + '\'' +
                '}';
    }
}
