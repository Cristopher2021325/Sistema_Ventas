package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Usuario;
import com.clopez.Sistema_Ventas.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public List<Usuario> getAll(){
        return usuarioService.getAllUsuario();
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuario usuario){
        try {
            Usuario createUsuarios = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(createUsuarios, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario){
        try {
            Usuario updateUsuarios = usuarioService.updateUsuario(id, usuario);
            return new ResponseEntity<>(updateUsuarios, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Integer id){
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario eliminado");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}