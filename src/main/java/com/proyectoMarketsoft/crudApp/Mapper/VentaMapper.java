package com.proyectoMarketsoft.crudApp.Mapper;

import com.proyectoMarketsoft.crudApp.DTO.AdministradorDTO;
import com.proyectoMarketsoft.crudApp.DTO.ClienteDTO;
import com.proyectoMarketsoft.crudApp.DTO.ProductoDTO;
import com.proyectoMarketsoft.crudApp.DTO.request.VentaRequestDTO;
import com.proyectoMarketsoft.crudApp.DTO.response.VentaResponseDTO;
import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import com.proyectoMarketsoft.crudApp.DTO.response.ProductoVentaResponseDTO;

import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;
@Component
public class VentaMapper {

    // Convertir de VentaRequestDTO a Venta (Para POST y actualización)
    public static Venta toEntity(VentaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Venta venta = new Venta();
        venta.setDescuento(dto.getDescuento());
        venta.setFechaVenta(dto.getFechaVenta());

        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(dto.getIdCliente().getIdCliente());
            venta.setCliente(cliente);
        }

        if (dto.getIdAdmin() != null) {
            Administrador administrador = new Administrador();
            administrador.setIdAdmin(dto.getIdAdmin().getIdAdmin());
            venta.setAdministrador(administrador);
        }

        if (dto.getProductoVentas() != null) {
            List<ProductoVenta> productosVenta = dto.getProductoVentas().stream().map(productoDto -> {
                ProductoVenta productoVenta = new ProductoVenta();
                productoVenta.setCantidad(productoDto.getCantidad());

                if (productoDto.getProducto() != null) {
                    Producto producto = new Producto();
                    producto.setIdProducto(productoDto.getProducto().getIdProducto());
                    productoVenta.setProducto(producto);
                }

                productoVenta.setVenta(venta);
                return productoVenta;
            }).collect(Collectors.toList());

            venta.setProductoVentas(productosVenta);
        }

        return venta;
    }

    // Convertir de Venta a VentaResponseDTO (Para GET)
    public VentaResponseDTO toDTO(Venta venta) {
        if (venta == null) {
            return null;
        }

        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setIdventa(venta.getIdventa());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTotalFinal(venta.getTotalFinal());
        dto.setIva(venta.getIva());
        dto.setPrecioVenta(venta.getPrecioVenta());
        dto.setDescuento(venta.getDescuento());

        if (venta.getCliente() != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(venta.getCliente().getIdCliente());
            clienteDTO.setNombreCliente(venta.getCliente().getNombreCliente());
            clienteDTO.setApellidoCliente(venta.getCliente().getApellidoCliente());
            dto.setCliente(clienteDTO);
        }

        if (venta.getAdministrador() != null) {
            AdministradorDTO adminDTO = new AdministradorDTO();
            adminDTO.setIdAdmin(venta.getAdministrador().getIdAdmin());
            adminDTO.setNombreAdmin(venta.getAdministrador().getNombreAdmin());
            dto.setAdministrador(adminDTO);
        }

        if (venta.getProductoVentas() != null) {
            List<ProductoVentaResponseDTO> productosVenta = venta.getProductoVentas().stream().map(productoVenta -> {
                ProductoVentaResponseDTO productoDTO = new ProductoVentaResponseDTO();
                productoDTO.setIdProductoVenta(productoVenta.getIdProductoVenta());
                productoDTO.setCantidad(productoVenta.getCantidad());
                productoDTO.setPrecioUnitario(productoVenta.getPrecioUnitario());

                if (productoVenta.getProducto() != null) {
                    ProductoDTO prodDTO = new ProductoDTO();
                    prodDTO.setIdProducto(productoVenta.getProducto().getIdProducto());
                    prodDTO.setNombreProd(productoVenta.getProducto().getNombreProd());
                    prodDTO.setMarcaProd(productoVenta.getProducto().getMarcaProd());
                    productoDTO.setProducto(prodDTO);
                }
                return productoDTO;
            }).collect(Collectors.toList());
            dto.setProductoVenta(productosVenta);
        }

        return dto;
    }
}
