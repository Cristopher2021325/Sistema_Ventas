package com.clopez.Sistema_Ventas.Controller;

import com.clopez.Sistema_Ventas.Entity.Producto;
import com.clopez.Sistema_Ventas.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoViewController {

    private final ProductoService productoService;

    public ProductoViewController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String mostrarProductos(@RequestParam(required = false) Integer buscar,
                                   @RequestParam(required = false) Integer editar,
                                   Model model) {
        cargarVista(model, buscar, editar);
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@RequestParam Integer codigoProducto,
                                  @RequestParam String nombreProducto,
                                  @RequestParam BigDecimal precio,
                                  @RequestParam Integer stock,
                                  @RequestParam Integer estado,
                                  RedirectAttributes redirectAttributes) {
        try {
            Producto producto = new Producto();
            producto.setCodigoProducto(codigoProducto);
            producto.setNombreProducto(nombreProducto);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setEstado(estado);
            productoService.saveProducto(producto);
            redirectAttributes.addFlashAttribute("success", "Producto guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productoService.deleteProducto(id);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    private void cargarVista(Model model, Integer buscar, Integer editar) {
        List<Producto> productos;
        if (buscar != null) {
            Producto encontrado = productoService.getById(buscar);
            productos = encontrado != null ? List.of(encontrado) : Collections.emptyList();
            if (encontrado == null) {
                model.addAttribute("error", "No se encontró un producto con el código indicado.");
            }
        } else {
            productos = productoService.getAllProducto();
        }

        Producto productoForm = editar != null ? productoService.getById(editar) : null;
        if (productoForm == null) {
            productoForm = new Producto();
            productoForm.setEstado(1);
            productoForm.setPrecio(BigDecimal.ZERO);
            productoForm.setStock(0);
        }

        model.addAttribute("productos", productos);
        model.addAttribute("productoForm", productoForm);
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", editar != null && productoForm.getCodigoProducto() != null);
    }
}
