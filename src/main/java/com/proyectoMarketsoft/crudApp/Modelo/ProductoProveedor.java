package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_productoprov")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoProveedor {

    @EmbeddedId
    private ProductoProveedorID id;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "Tbl_producto_ID_Producto", referencedColumnName = "ID_Producto")
    private Producto producto;

    @ManyToOne
    @MapsId("proveedorId")
    @JoinColumn(name = "Tbl_proveedor_ID_Proveedor", referencedColumnName = "Id_Proveedor")
    private Proveedor proveedor;

    @Column(name = "TblProducto_Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "TblProducto_Precio_unit", nullable = false)
    private BigDecimal precioUnitario;
}
