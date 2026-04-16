package com.clopez.Sistema_Ventas.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Inicio
    @GetMapping("/")
    public String inicio() {
        return "redirect:/index";
    }

    // Mostrar Login
    @GetMapping("/index")
    public String mostrarLogin() {
        return "index";
    }

    // Procesar Login
    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        // Credenciales de ejemplo (admin)
        String userAdmin    = "admin";
        String passAdmin    = "1234";

        // Credencial de usuario normal
        String userNormal   = "usuario";
        String passNormal   = "1234";

        if (usuario.equals(userAdmin) && password.equals(passAdmin)) {
            session.setAttribute("usuarioLogeado", usuario);
            session.setAttribute("rolUsuario", "admin");
            return "redirect:/home";

        } else if (usuario.equals(userNormal) && password.equals(passNormal)) {
            session.setAttribute("usuarioLogeado", usuario);
            session.setAttribute("rolUsuario", "usuario");
            return "redirect:/home";

        } else {
            model.addAttribute("error", "Usuario y contraseña incorrectos");
            return "index";
        }
    }

    // Cerrar sesión
    @GetMapping("/cerrarsesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    // Proteger ruta sin Spring Security
    @GetMapping("/home-login")
    public String mostrarHome(HttpSession session) {
        if (session.getAttribute("usuarioLogeado") == null) {
            return "redirect:/index";
        }
        return "home";
    }
}
