package com.clopez.Sistema_Ventas.Service;

import com.clopez.Sistema_Ventas.Entity.Cliente;
import com.clopez.Sistema_Ventas.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImplements implements ClienteService{
    private final ClienteRepository clienteRepository;

    public ClienteServiceImplements(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getByIdCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) throws RuntimeException {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}