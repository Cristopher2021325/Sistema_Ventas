package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Producto;
import com.clopez.Sistema_Ventas.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImplements implements ProductoService {
    public final ProductoRepository productoRepository;

    public ProductoServiceImplements(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto saveProducto(Producto producto) throws RuntimeException {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Integer id, Producto producto) {
        producto.setCodigoProducto(id);
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
