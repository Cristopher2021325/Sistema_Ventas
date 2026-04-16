package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> getAllCliente();
    Cliente getByIdCliente(Integer id);
    Cliente saveCliente(Cliente cliente) throws RuntimeException;
    Cliente updateCliente(Integer id, Cliente cliente);
    void deleteCliente(Integer id);
}