package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Usuario;
import com.clopez.Sistema_Ventas.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarUsuarios(@RequestParam(required = false) Integer buscar,
                                  @RequestParam(required = false) Integer editar,
                                  Model model) {
        cargarVista(model, buscar, editar);
        return "usuarios";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@RequestParam Integer codigoUsuario,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String rol,
                                 @RequestParam Integer estado,
                                 RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCodigoUsuario(codigoUsuario);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEmail(email);
            usuario.setRol(rol);
            usuario.setEstado(estado);
            usuarioService.saveUsuario(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el usuario: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deleteUsuario(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

    private void cargarVista(Model model, Integer buscar, Integer editar) {
        List<Usuario> usuarios;
        if (buscar != null) {
            Usuario encontrado = usuarioService.getById(buscar);
            usuarios = encontrado != null ? List.of(encontrado) : Collections.emptyList();
            if (encontrado == null) {
                model.addAttribute("error", "No se encontró un usuario con el código indicado.");
            }
        } else {
            usuarios = usuarioService.getAllUsuario();
        }

        Usuario usuarioForm = editar != null ? usuarioService.getById(editar) : null;
        if (usuarioForm == null) {
            usuarioForm = new Usuario();
            usuarioForm.setEstado(1);
            usuarioForm.setRol("VENDEDOR");
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioForm", usuarioForm);
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", editar != null && usuarioForm.getCodigoUsuario() != null);
    }
}
