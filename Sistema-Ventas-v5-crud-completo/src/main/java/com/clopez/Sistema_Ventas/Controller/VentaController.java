package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Venta;
import com.clopez.Sistema_Ventas.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAll(){
        return ventaService.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta){
        try {
            Venta createVentas = ventaService.saveVenta(venta);
            return new ResponseEntity<>(createVentas, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenta(@PathVariable Integer id, @Valid @RequestBody Venta venta){
        try {
            Venta updateVentas = ventaService.updateVenta(id, venta);
            return new ResponseEntity<>(updateVentas, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVenta(@PathVariable Integer id){
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.ok("Venta eliminada");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}