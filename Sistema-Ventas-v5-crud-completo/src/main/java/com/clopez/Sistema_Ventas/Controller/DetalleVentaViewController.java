package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.DetalleVentas;
import com.clopez.Sistema_Ventas.Entity.Producto;
import com.clopez.Sistema_Ventas.Entity.Venta;
import com.clopez.Sistema_Ventas.Service.DetalleVentaService;
import com.clopez.Sistema_Ventas.Service.ProductoService;
import com.clopez.Sistema_Ventas.Service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/detalleventa")
public class DetalleVentaViewController {

    private final DetalleVentaService detalleVentaService;
    private final ProductoService productoService;
    private final VentaService ventaService;

    public DetalleVentaViewController(DetalleVentaService detalleVentaService,
                                      ProductoService productoService,
                                      VentaService ventaService) {
        this.detalleVentaService = detalleVentaService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    @GetMapping
    public String mostrarDetalleVenta(@RequestParam(required = false) Integer buscar,
                                      @RequestParam(required = false) Integer editar,
                                      Model model) {
        cargarVista(model, buscar, editar);
        return "detalleventa";
    }

    @PostMapping("/guardar")
    public String guardarDetalle(@RequestParam Integer codigoDetalleVenta,
                                 @RequestParam Integer cantidad,
                                 @RequestParam BigDecimal precioUnitario,
                                 @RequestParam Integer productoId,
                                 @RequestParam Integer ventaId,
                                 RedirectAttributes redirectAttributes) {
        try {
            Producto producto = productoService.getById(productoId);
            Venta venta = ventaService.getByIdVenta(ventaId);

            if (producto == null || venta == null) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un producto y una venta válidos.");
                return "redirect:/detalleventa";
            }

            DetalleVentas detalle = new DetalleVentas();
            detalle.setCodigoDetalleVenta(codigoDetalleVenta);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(precioUnitario.multiply(BigDecimal.valueOf(cantidad)));
            detalle.setProducto(producto);
            detalle.setVenta(venta);
            detalleVentaService.saveDetalleVenta(detalle);
            redirectAttributes.addFlashAttribute("success", "Detalle de venta guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el detalle de venta: " + e.getMessage());
        }
        return "redirect:/detalleventa";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDetalle(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            detalleVentaService.deleteDetalleVenta(id);
            redirectAttributes.addFlashAttribute("success", "Detalle de venta eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el detalle de venta: " + e.getMessage());
        }
        return "redirect:/detalleventa";
    }

    private void cargarVista(Model model, Integer buscar, Integer editar) {
        List<DetalleVentas> detalles;
        if (buscar != null) {
            DetalleVentas encontrado = detalleVentaService.getById(buscar);
            detalles = encontrado != null ? List.of(encontrado) : Collections.emptyList();
            if (encontrado == null) {
                model.addAttribute("error", "No se encontró un detalle con el código indicado.");
            }
        } else {
            detalles = detalleVentaService.getAll();
        }

        DetalleVentas detalleForm = editar != null ? detalleVentaService.getById(editar) : null;
        if (detalleForm == null) {
            detalleForm = new DetalleVentas();
            detalleForm.setCantidad(1);
            detalleForm.setPrecioUnitario(BigDecimal.ZERO);
            detalleForm.setSubtotal(BigDecimal.ZERO);
        }

        model.addAttribute("detalles", detalles);
        model.addAttribute("detalleForm", detalleForm);
        model.addAttribute("productos", productoService.getAllProducto());
        model.addAttribute("ventas", ventaService.getAll());
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", editar != null && detalleForm.getCodigoDetalleVenta() != null);
    }
}
