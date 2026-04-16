package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Venta;
import com.clopez.Sistema_Ventas.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImplements implements VentaService{
    public final VentaRepository ventaRepository;

    public VentaServiceImplements(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getByIdVenta(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta saveVenta(Venta venta) throws RuntimeException {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void deleteVenta(Integer id) {
        ventaRepository.deleteById(id);
    }
}
