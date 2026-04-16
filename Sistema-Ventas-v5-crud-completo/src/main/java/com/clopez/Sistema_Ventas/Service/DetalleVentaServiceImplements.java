package com.clopez.Sistema_Ventas.Service;
import com.clopez.Sistema_Ventas.Entity.DetalleVentas;
import com.clopez.Sistema_Ventas.Repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService{
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImplements(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVentas> getAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVentas getById(Integer id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleVentas saveDetalleVenta(DetalleVentas detalleVentas) throws RuntimeException {
        return detalleVentaRepository.save(detalleVentas);
    }

    @Override
    public DetalleVentas updateDetalleVenta(Integer id, DetalleVentas detalleVentas) {
        return detalleVentaRepository.save(detalleVentas);
    }

    @Override
    public void deleteDetalleVenta(Integer id) {
        detalleVentaRepository.deleteById(id);
    }
}