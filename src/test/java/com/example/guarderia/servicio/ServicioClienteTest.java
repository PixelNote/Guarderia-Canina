package com.example.guarderia.servicio;

import com.example.guarderia.datos.Cliente;
import com.example.guarderia.repositorio.IClienteRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServicioClienteTest {

    @InjectMocks
    private ServicioCliente servicioCliente;

    @Mock
    private IClienteRepositorio clienteRepositorio;

    @Test
    void given_nuevo_cliente_when_invoke_agregarCliente_then_return_saveCliente() {
        Cliente cliente = new Cliente(123456, "Perez", "calle 2");
        Mockito.when(clienteRepositorio.save(any(Cliente.class)))
                .thenReturn(cliente);
        Cliente resultado = servicioCliente.agregarCliente(cliente);
        Assertions.assertEquals(cliente, resultado);
        Mockito.verify(clienteRepositorio).save(any());
    }

    @Test
    void when_obtenerClientes_then_return_lista_de_clientes() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(1, "Andres", "Calle 1");
        Cliente cliente2 = new Cliente(2, "Juan", "Calle 2");
        clientes.add(cliente1);
        clientes.add(cliente2);

        Mockito.when(clienteRepositorio.findAll()).thenReturn(clientes);

        List<Cliente> resultado = servicioCliente.obtenerClientes();

        Assertions.assertEquals(clientes, resultado);
        Mockito.verify(clienteRepositorio).findAll();


    }

}
