package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Cliente;
import com.clopez.Sistema_Ventas.Service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String mostrarCliente(@RequestParam(required = false) Integer buscar,
                                 @RequestParam(required = false) Integer editar,
                                 Model model) {
        cargarVista(model, buscar, editar);
        return "cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@RequestParam Integer dpiCliente,
                                 @RequestParam String nombreCliente,
                                 @RequestParam String apellidoCliente,
                                 @RequestParam String direccionCliente,
                                 @RequestParam Integer estadoCliente,
                                 RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = new Cliente();
            cliente.setDpiCliente(dpiCliente);
            cliente.setNombreCliente(nombreCliente);
            cliente.setApellidoCliente(apellidoCliente);
            cliente.setDireccionCliente(direccionCliente);
            cliente.setEstadoCliente(estadoCliente);
            clienteService.saveCliente(cliente);
            redirectAttributes.addFlashAttribute("success", "Cliente guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el cliente: " + e.getMessage());
        }
        return "redirect:/cliente";
    }

    @PostMapping("/eliminar/{dpi}")
    public String eliminarCliente(@PathVariable Integer dpi, RedirectAttributes redirectAttributes) {
        try {
            clienteService.deleteCliente(dpi);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el cliente: " + e.getMessage());
        }
        return "redirect:/cliente";
    }

    private void cargarVista(Model model, Integer buscar, Integer editar) {
        List<Cliente> clientes;
        if (buscar != null) {
            Cliente encontrado = clienteService.getByIdCliente(buscar);
            clientes = encontrado != null ? List.of(encontrado) : Collections.emptyList();
            if (encontrado == null) {
                model.addAttribute("error", "No se encontró un cliente con el DPI indicado.");
            }
        } else {
            clientes = clienteService.getAllCliente();
        }

        Cliente clienteFormulario = editar != null ? clienteService.getByIdCliente(editar) : null;
        if (clienteFormulario == null) {
            clienteFormulario = new Cliente();
            clienteFormulario.setEstadoCliente(1);
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteForm", clienteFormulario);
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", editar != null && clienteFormulario.getDpiCliente() != null);
    }
}
