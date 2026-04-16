package com.clopez.Sistema_Ventas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String mostrarHome(){
        return "home";
    }

    @GetMapping("/home-cliente")
    public String mostrarCliente(){
        return "cliente";
    }
}
