package com.proyectoMarketsoft.crudApp.DTO.response;

import com.proyectoMarketsoft.crudApp.DTO.ProductoDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class ProductoVentaResponseDTO {

    private Integer idProductoVenta;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private ProductoDTO producto;
}
