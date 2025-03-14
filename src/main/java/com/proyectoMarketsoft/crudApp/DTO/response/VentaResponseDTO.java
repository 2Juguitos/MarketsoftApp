package com.proyectoMarketsoft.crudApp.DTO.response;

import com.proyectoMarketsoft.crudApp.DTO.AdministradorDTO;
import com.proyectoMarketsoft.crudApp.DTO.ClienteDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class VentaResponseDTO {
    private Date fechaVenta;
    private BigDecimal descuento;
    private BigDecimal totalFinal;
    private BigDecimal iva;
    private BigDecimal precioVenta;
    private Integer idventa;
    private ClienteDTO cliente;
    private AdministradorDTO administrador;
    private List<ProductoVentaResponseDTO> productoVenta;
}
