package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Cliente;
import com.clopez.Sistema_Ventas.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    public final ClienteService clienteService;

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteService.getAllCliente();
    }

    @PostMapping
    public ResponseEntity<Object> createCliente(@Valid @RequestBody Cliente cliente){
        try {
            Cliente createCliente = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(createCliente, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClientes(@PathVariable Integer id, @Valid @RequestBody Cliente cliente){
        try {
            Cliente updateCliente = clienteService.updateCliente(id,cliente);
            return new ResponseEntity<>(updateCliente,HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientes(@PathVariable Integer id){
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
