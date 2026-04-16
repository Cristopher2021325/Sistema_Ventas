package com.clopez.Sistema_Ventas.Repository;

import com.clopez.Sistema_Ventas.Entity.DetalleVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentas,Integer> {
}
