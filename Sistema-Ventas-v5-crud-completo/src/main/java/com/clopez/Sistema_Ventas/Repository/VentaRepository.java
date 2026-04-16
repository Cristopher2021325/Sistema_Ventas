package com.clopez.Sistema_Ventas.Repository;

import com.clopez.Sistema_Ventas.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer> {
}