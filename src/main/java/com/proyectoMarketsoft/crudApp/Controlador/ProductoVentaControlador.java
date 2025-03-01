package com.proyectoMarketsoft.crudApp.Controlador;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoVenta;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoVentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productoventa")
public class ProductoVentaControlador {

    @Autowired
    private ProductoVentaRepositorio prodVentaRepositorio;

    @GetMapping
    public List<ProductoVenta> listarTodos() {
        return prodVentaRepositorio.findAll();
    }

    @PostMapping
    public ProductoVenta crearProdVenta(@RequestBody ProductoVenta prodVenta) {
        return prodVentaRepositorio.save(prodVenta);
    }

    @GetMapping("/{id}")
    public ProductoVenta obtenerPorId(@PathVariable Integer ID) {
        return prodVentaRepositorio.findById(ID).orElse(null);
    }

    @PutMapping("/{id}")
    public ProductoVenta actualizarProdVenta(@PathVariable Integer id, @RequestBody ProductoVenta prodVentaDetalles) {
        return prodVentaRepositorio.findById(id).map(prodVenta -> {
            prodVenta.setPrecioUnitario(prodVentaDetalles.getPrecioUnitario());
            prodVenta.setCantidad(prodVentaDetalles.getCantidad());
            prodVenta.setProducto(prodVentaDetalles.getProducto());
            prodVenta.setVenta(prodVentaDetalles.getVenta());
            return prodVentaRepositorio.save(prodVenta);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarProdVenta(@PathVariable Integer id) {
        prodVentaRepositorio.deleteById(id);
    }
}
