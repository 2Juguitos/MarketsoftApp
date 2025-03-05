package com.proyectoMarketsoft.crudApp.Servicio;

import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import com.proyectoMarketsoft.crudApp.Modelo.Venta;
import com.proyectoMarketsoft.crudApp.Repositorio.InventarioRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVenta {

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private InventarioRepositorio inventarioRepository;

    /**
     * Crea una venta calculando el precioVenta a partir de los detalles.
     * Para cada ProductoVenta se obtiene el precio unitario desde Inventario
     * y se calcula el subtotal (precioUnitario * cantidad) que se suma para obtener el precioVenta.
     *
     *
     */


    @Transactional
    public Venta crearVenta(Venta venta) {
        BigDecimal totalVenta = BigDecimal.ZERO;
        if (venta.getProductoVenta() != null) {
            for (ProductoVenta detalle : venta.getProductoVenta()) {
                // Se obtiene el ID del producto asociado en el detalle
                Integer idProducto = detalle.getProducto().getIdProducto();
                // Se consulta el precio unitario calculado a partir de Inventario
                BigDecimal precioUnitarioCalculado = inventarioRepository.sumarPreciosPorProducto(idProducto);
                // Asigna el precio unitario obtenido al detalle
                detalle.setPrecioUnitario(precioUnitarioCalculado);
                // Calcula el subtotal para el detalle
                int cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 0;
                BigDecimal subtotal = precioUnitarioCalculado.multiply(new BigDecimal(cantidad));
                // Suma al total de la venta
                totalVenta = totalVenta.add(subtotal);
                // Asigna la referencia de venta al detalle
                detalle.setVenta(venta);
            }
        }
        venta.setPrecioVenta(totalVenta);
        return ventaRepositorio.save(venta);
    }

    /**
     * Obtiene una venta por ID.
     */
    public Optional<Venta> getVentaById(Integer id) {
        return ventaRepositorio.findById(id);
    }

    /**
     * Obtiene todas las ventas.
     */
    public List<Venta> getAllVentas() {
        return ventaRepositorio.findAll();
    }

    /**
     * Actualiza una venta existente.
     * Se actualizan los campos principales y se recalcula el precioVenta a partir de la lista de ProductoVenta.
     */
    @Transactional
    public Venta actualizarVenta(Integer id, Venta ventaActualizada) {
        return ventaRepositorio.findById(id).map(ventaExistente -> {
            // Actualizamos campos simples
            ventaExistente.setDescuento(ventaActualizada.getDescuento());
            ventaExistente.setFechaVenta(ventaActualizada.getFechaVenta());
            ventaExistente.setCliente(ventaActualizada.getCliente());
            ventaExistente.setAdministrador(ventaActualizada.getAdministrador());

            // Actualizamos la lista de detalles y reemplazamos la lista existente
            if (ventaActualizada.getProductoVenta() != null) {
                ventaExistente.getProductoVenta().clear();
                BigDecimal totalVenta = BigDecimal.ZERO;
                for (ProductoVenta detalle : ventaActualizada.getProductoVenta()) {
                    Integer idProducto = detalle.getProducto().getIdProducto();
                    BigDecimal precioUnitarioCalculado = inventarioRepository.sumarPreciosPorProducto(idProducto);
                    detalle.setPrecioUnitario(precioUnitarioCalculado);
                    int cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 0;
                    BigDecimal subtotal = precioUnitarioCalculado.multiply(new BigDecimal(cantidad));
                    totalVenta = totalVenta.add(subtotal);
                    // Asigna la venta existente al detalle
                    detalle.setVenta(ventaExistente);
                    ventaExistente.getProductoVenta().add(detalle);
                }
                ventaExistente.setPrecioVenta(totalVenta);
            }
            return ventaRepositorio.save(ventaExistente);
        }).orElse(null);
    }

    /**
     * Elimina una venta existente.
     */
    @Transactional
    public boolean eliminarVenta(Integer id) {
        Optional<Venta> ventaOpt = ventaRepositorio.findById(id);
        if (ventaOpt.isPresent()) {
            ventaRepositorio.delete(ventaOpt.get());
            return true;
        }
        return false;
    }
}
