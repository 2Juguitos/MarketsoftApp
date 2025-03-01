package com.proyectoMarketsoft.crudApp.Modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoProveedorID implements Serializable {

    @Column(name = "Tbl_producto_ID_Producto")
    private Integer productoId;

    @Column(name = "Tbl_proveedor_ID_Proveedor")
    private Integer proveedorId;
}
