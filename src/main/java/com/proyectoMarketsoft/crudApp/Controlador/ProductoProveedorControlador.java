package com.proyectoMarketsoft.crudApp.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedor;
import com.proyectoMarketsoft.crudApp.Modelo.ProductoProveedorID;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoProveedorRepositorio;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto_proveedor")
public class ProductoProveedorControlador {

    @Autowired
    private ProductoProveedorRepositorio productoProveedorRepositorio;

    @GetMapping
    public List<ProductoProveedor> listarTodos() {
        return productoProveedorRepositorio.findAll();
    }

    @PostMapping
    public ProductoProveedor crear(@RequestBody ProductoProveedor productoProveedor) {
        return productoProveedorRepositorio.save(productoProveedor);
    }

    @GetMapping("/{idProducto}/{idProveedor}")
    public ResponseEntity<ProductoProveedor> obtenerPorId(
            @PathVariable int idProducto,
            @PathVariable int idProveedor) {

        ProductoProveedorID ID = new ProductoProveedorID(idProducto, idProveedor);
        Optional<ProductoProveedor> productoProveedor = productoProveedorRepositorio.findById(ID);

        return productoProveedor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}/{idProveedor}")
    public ResponseEntity<Void> eliminar(
            @PathVariable int idProducto,
            @PathVariable int idProveedor) {

        ProductoProveedorID id = new ProductoProveedorID(idProducto, idProveedor);
        if (productoProveedorRepositorio.existsById(id)) {
            productoProveedorRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
