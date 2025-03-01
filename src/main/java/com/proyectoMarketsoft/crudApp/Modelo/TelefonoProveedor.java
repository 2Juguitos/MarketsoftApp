package com.proyectoMarketsoft.crudApp.Modelo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "tbl_telprov")
@Data

public class TelefonoProveedor {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_Telprov")
        private Integer idTelprov;

        @Column(name = "Tel_Prov", nullable = false)
        private Integer telProv;

        @ManyToOne
        @JoinColumn(name = "Tbl_Proveedor_Id_Proveedor", nullable = false)
        private Proveedor proveedor;

}
