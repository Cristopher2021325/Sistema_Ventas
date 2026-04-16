package com.clopez.Sistema_Ventas.Repository;

import com.clopez.Sistema_Ventas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Integer> {
}
