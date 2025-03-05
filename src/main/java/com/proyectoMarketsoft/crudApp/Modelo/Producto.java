package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Producto")
@Table(name = "tbl_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private Integer idProducto;

    @Column(name = "Marca_Prod", nullable = false)
    private String marcaProd;

    @Column(name = "Nombre_Prod", nullable = false)
    private String nombreProd;

    /** Relación con Inventarios **/
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventario> inventarios;

    /** Relación con Administrador **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tbl_Administrador_ID_Admin", nullable = false)
    @JsonIgnore // Evita serialización infinita
    private Administrador administrador;

    /** Relación con Categoría **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_Categoria_idtbl_Categoria", nullable = false)
    @JsonIgnore // Evita serialización infinita
    private Categoria categoria;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", marcaProd='" + marcaProd + '\'' +
                ", nombreProd='" + nombreProd + '\'' +
                '}';
    }
}
