package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Producto;
import com.clopez.Sistema_Ventas.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getAll(){
        return productoService.getAllProducto();
    }

    @PostMapping
    public ResponseEntity<Object> createProducto(@Valid @RequestBody Producto producto){
        try {
            Producto createProductos = productoService.saveProducto(producto);
            return new ResponseEntity<>(createProductos, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable Integer id, @Valid @RequestBody Producto producto){
        try {
            Producto updateProductos = productoService.updateProducto(id, producto);
            return new ResponseEntity<>(updateProductos, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Integer id){
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
