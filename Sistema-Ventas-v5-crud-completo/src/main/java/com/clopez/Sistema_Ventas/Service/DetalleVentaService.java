package com.clopez.Sistema_Ventas.Service;


import com.clopez.Sistema_Ventas.Entity.DetalleVentas;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVentas> getAll();
    DetalleVentas getById(Integer id);
    DetalleVentas saveDetalleVenta(DetalleVentas detalleVentas) throws RuntimeException;
    DetalleVentas updateDetalleVenta(Integer id,DetalleVentas detalleVentas);
    void deleteDetalleVenta(Integer id);
}
