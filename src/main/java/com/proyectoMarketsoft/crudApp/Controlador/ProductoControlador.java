package com.proyectoMarketsoft.crudApp.Controlador;

import com.proyectoMarketsoft.crudApp.Modelo.Producto;
import com.proyectoMarketsoft.crudApp.Repositorio.ProductoRepositorio;
import com.proyectoMarketsoft.crudApp.Modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private ProductoRepositorio productoRepositorio;


    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepositorio.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoRepositorio.save(producto);
        return ResponseEntity.status(201).body(savedProducto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto productoActualizado) {
        if (productoRepositorio.existsById(id)) {
            productoActualizado.setIdProducto(id);
            Producto updatedProducto = productoRepositorio.save(productoActualizado);
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Integer idCategoria) {
        List<Producto> productos = productoRepositorio.findByCategoria_IdCategoria(idCategoria);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
