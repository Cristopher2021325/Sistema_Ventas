package com.clopez.Sistema_Ventas.Repository;


import com.clopez.Sistema_Ventas.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}