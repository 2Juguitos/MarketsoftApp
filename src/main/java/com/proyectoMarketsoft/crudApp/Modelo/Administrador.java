package com.proyectoMarketsoft.crudApp.Modelo;
import com.fasterxml.jackson.annotation.*;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Modelo.Inventario;
import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Administrador")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tbl_administrador")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAdmin")
public class Administrador {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_Admin")
        private Integer idAdmin;

        @Column(name = "Nombre_Admin", nullable = false, length = 45)
        private String nombreAdmin;

        // Campos para autenticación
        @Column(name = "username", unique = true, nullable = false, length = 50)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "rol", nullable = false, length = 20)
        private String rol = "ROLE_ADMIN";  // Valor por defecto

        // Relaciones
        @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<Producto> productos;

        @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<Cliente> clientes;

        @OneToMany(mappedBy ="administrador", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<Venta> ventas;

        @OneToMany(mappedBy = "administrador")
        @JsonBackReference
        private List<Inventario> inventarios;
}
