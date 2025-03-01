package com.proyectoMarketsoft.crudApp.Modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.NonNull;




    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Table(name = "tbl_proveedor")
    public class Proveedor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id_Proveedor")
        private int idProveedor;

        @NonNull
        @Column(name = "Correo_Proveedor")
        private String correoProveedor;

        @NonNull
        @Column(name = "Empresa_Prov")
        private String empresaProv;

        @NonNull
        @Column(name = "Nombre_Prov")
        private String nombreProv;

        @NonNull
        @Column(name = "Apellido_Prov")
        private String apellidoProv;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "Tbl_Administrador_ID_Admin", referencedColumnName = "ID_Admin", nullable = false)
        private Administrador administrador;


        public int getIdProveedor() {
            return idProveedor;
        }

        public void setIdProveedor(int idProveedor) {
            this.idProveedor = idProveedor;
        }

        public String getCorreoProveedor() {
            return correoProveedor;
        }

        public void setCorreoProveedor(String correoProveedor) {
            this.correoProveedor = correoProveedor;
        }

        public String getEmpresaProv() {
            return empresaProv;
        }

        public void setEmpresaProv(String empresaProv) {
            this.empresaProv = empresaProv;
        }

        public String getNombreProv() {
            return nombreProv;
        }

        public void setNombreProv(String nombreProv) {
            this.nombreProv = nombreProv;
        }

        public String getApellidoProv() {
            return apellidoProv;
        }

        public void setApellidoProv(String apellidoProv) {
            this.apellidoProv = apellidoProv;
        }

        public Administrador getAdministrador() {
            return administrador;
        }

        public void setAdministrador(Administrador administrador) {
            this.administrador = administrador;
        }
    }


