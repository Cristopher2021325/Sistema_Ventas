package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> getAll();
    Venta getByIdVenta(Integer id);
    Venta saveVenta(Venta venta) throws RuntimeException;
    Venta updateVenta(Integer id,Venta venta);
    void deleteVenta(Integer id);
}
