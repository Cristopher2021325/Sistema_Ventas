package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProducto();
    Producto getById(Integer id);
    Producto saveProducto(Producto producto) throws RuntimeException;
    Producto updateProducto(Integer id, Producto producto);
    void deleteProducto(Integer id);
}
