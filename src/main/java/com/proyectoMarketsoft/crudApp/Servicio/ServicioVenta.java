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

        // Asigna el total calculado al precioVenta de la venta
        venta.setPrecioVenta(totalVenta);

        // Persiste la venta (con cascade en ProductoVenta se guardan también los detalles)
        return ventaRepositorio.save(venta);
    }

    public Optional<Venta> getVentaById(Integer id) {
        return ventaRepositorio.findById(id);
    }

    public List<Venta> getAllVentas() {
        return ventaRepositorio.findAll();
    }
}

