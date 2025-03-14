package com.proyectoMarketsoft.crudApp.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProductoVentaRequestDTO {

    private Integer cantidad;
    private ProductoRequestDTO producto;
}
