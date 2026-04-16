package com.clopez.Sistema_Ventas.Repository;

import com.clopez.Sistema_Ventas.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
