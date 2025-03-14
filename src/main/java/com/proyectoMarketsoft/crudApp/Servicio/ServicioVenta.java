package com.proyectoMarketsoft.crudApp.Servicio;

import com.proyectoMarketsoft.crudApp.DTO.request.VentaRequestDTO;
import com.proyectoMarketsoft.crudApp.DTO.response.VentaResponseDTO;
import com.proyectoMarketsoft.crudApp.Mapper.VentaMapper;
import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import com.proyectoMarketsoft.crudApp.Repositorio.VentaRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.InventarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioVenta {

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private InventarioRepositorio inventarioRepository;

    @Autowired
    private VentaMapper ventaMapper;

    /**
     * Crea una venta a partir de un VentaRequestDTO.
     * Calcula el precioVenta utilizando el precio unitario desde Inventario.
     */
    @Transactional
    public VentaResponseDTO crearVenta(VentaRequestDTO ventaRequestDTO) {
        Venta venta = ventaMapper.toEntity(ventaRequestDTO); // Convertimos el DTO a Entidad
        BigDecimal totalVenta = BigDecimal.ZERO;

        if (venta.getProductoVentas() != null) {
            for (ProductoVenta detalle : venta.getProductoVentas()) {
                Integer idProducto = detalle.getProducto().getIdProducto();
                BigDecimal precioUnitarioCalculado = inventarioRepository.sumarPreciosPorProducto(idProducto);

                detalle.setPrecioUnitario(precioUnitarioCalculado);

                int cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 0;
                BigDecimal subtotal = precioUnitarioCalculado.multiply(new BigDecimal(cantidad));
                totalVenta = totalVenta.add(subtotal);

                detalle.setVenta(venta);
            }
        }

        venta.setPrecioVenta(totalVenta);

        Venta ventaGuardada = ventaRepositorio.save(venta);
        return ventaMapper.toDTO(ventaGuardada); // Convertimos la Entidad a DTO para la respuesta
    }

    /**
     * Obtiene una venta por su ID y la convierte a VentaResponseDTO.
     */
    public Optional<VentaResponseDTO> getVentaById(Integer id) {
        return ventaRepositorio.findById(id).map(ventaMapper::toDTO);
    }

    /**
     * Obtiene todas las ventas y las convierte a una lista de VentaResponseDTO.
     */
    public List<VentaResponseDTO> getAllVentas() {
        return ventaRepositorio.findAll()
                .stream()
                .map(ventaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarVenta(Integer id) {
        if (ventaRepositorio.existsById(id)) {
            // La eliminación en cascada se encarga de borrar los ProductoVenta asociados.
            ventaRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Venta no encontrada");
        }
}
}

