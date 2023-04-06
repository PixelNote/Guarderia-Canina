package com.example.guarderia.servicio;

import com.example.guarderia.dato.Cliente;
import com.example.guarderia.repositorio.IClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor

public class ServicioCliente {
    private IClienteRepositorio clienteRepositorio;

    public Cliente agregarCliente(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }
    public List<Cliente> obtenerClientes(){
        return clienteRepositorio.findAll();
    }
}
