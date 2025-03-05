package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_productoprov")
public class ProductoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Tbl_producto_ID_Producto", nullable = false)
    private Integer productoId;

    @Column(name = "Tbl_proveedor_ID_Proveedor", nullable = false)
    private Integer proveedorId;

    @Column(name = "TblProducto_Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "TblProducto_Precio_unit", nullable = false)
    private Double precioUnitario;
}