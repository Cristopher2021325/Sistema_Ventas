package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Cliente;
import com.clopez.Sistema_Ventas.Entity.Usuario;
import com.clopez.Sistema_Ventas.Entity.Venta;
import com.clopez.Sistema_Ventas.Service.ClienteService;
import com.clopez.Sistema_Ventas.Service.UsuarioService;
import com.clopez.Sistema_Ventas.Service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaViewController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public VentaViewController(VentaService ventaService, ClienteService clienteService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarVentas(@RequestParam(required = false) Integer buscar,
                                @RequestParam(required = false) Integer editar,
                                Model model) {
        cargarVista(model, buscar, editar);
        return "ventas";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@RequestParam Integer codigoVenta,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaVenta,
                               @RequestParam BigDecimal total,
                               @RequestParam Integer estado,
                               @RequestParam Integer clienteId,
                               @RequestParam Integer usuarioId,
                               RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteService.getByIdCliente(clienteId);
            Usuario usuario = usuarioService.getById(usuarioId);

            if (cliente == null || usuario == null) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un cliente y un usuario válidos.");
                return "redirect:/ventas";
            }

            Venta venta = new Venta();
            venta.setCodigoVenta(codigoVenta);
            venta.setFechaVenta(Date.valueOf(fechaVenta));
            venta.setTotal(total);
            venta.setEstado(estado);
            venta.setCliente(cliente);
            venta.setUsuario(usuario);
            ventaService.saveVenta(venta);
            redirectAttributes.addFlashAttribute("success", "Venta guardada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar la venta: " + e.getMessage());
        }
        return "redirect:/ventas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            ventaService.deleteVenta(id);
            redirectAttributes.addFlashAttribute("success", "Venta eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la venta: " + e.getMessage());
        }
        return "redirect:/ventas";
    }

    private void cargarVista(Model model, Integer buscar, Integer editar) {
        List<Venta> ventas;
        if (buscar != null) {
            Venta encontrada = ventaService.getByIdVenta(buscar);
            ventas = encontrada != null ? List.of(encontrada) : Collections.emptyList();
            if (encontrada == null) {
                model.addAttribute("error", "No se encontró una venta con el código indicado.");
            }
        } else {
            ventas = ventaService.getAll();
        }

        Venta ventaForm = editar != null ? ventaService.getByIdVenta(editar) : null;
        if (ventaForm == null) {
            ventaForm = new Venta();
            ventaForm.setEstado(1);
            ventaForm.setFechaVenta(Date.valueOf(LocalDate.now()));
            ventaForm.setTotal(BigDecimal.ZERO);
        }

        model.addAttribute("ventas", ventas);
        model.addAttribute("ventaForm", ventaForm);
        model.addAttribute("clientes", clienteService.getAllCliente());
        model.addAttribute("usuarios", usuarioService.getAllUsuario());
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", editar != null && ventaForm.getCodigoVenta() != null);
    }
}
