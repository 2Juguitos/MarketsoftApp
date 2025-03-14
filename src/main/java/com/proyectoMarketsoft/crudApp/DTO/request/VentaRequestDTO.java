package com.proyectoMarketsoft.crudApp.DTO.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Setter
@Getter
public class VentaRequestDTO {
    private BigDecimal descuento;
    private Date fechaVenta;
    private ClienteRequestDTO idCliente;
    private AdministradorRequestDTO idAdmin;
    private List<ProductoVentaRequestDTO> productoVentas;


}
