package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.DetalleVentas;
import com.clopez.Sistema_Ventas.Service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVentas> getAll(){
        return detalleVentaService.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> createDetalleVentas(@Valid @RequestBody DetalleVentas detalleVentas){
        try {
            DetalleVentas createDetalleVenta = detalleVentaService.saveDetalleVenta(detalleVentas);
            return new ResponseEntity<>(createDetalleVenta, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetalleVentas(@PathVariable Integer id, @Valid @RequestBody DetalleVentas detalleVentas){
        try {
            DetalleVentas updateDetalleVenta = detalleVentaService.updateDetalleVenta(id, detalleVentas);
            return new ResponseEntity<>(updateDetalleVenta, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetalleVenta(@PathVariable Integer id){
        try {
            detalleVentaService.deleteDetalleVenta(id);
            return ResponseEntity.ok("Detalle de venta eliminado");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
