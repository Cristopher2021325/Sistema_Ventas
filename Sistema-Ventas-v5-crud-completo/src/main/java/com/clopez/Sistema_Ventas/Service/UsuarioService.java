package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAllUsuario();
    Usuario getById(Integer id);
    Usuario saveUsuario(Usuario usuario) throws RuntimeException;
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);
}