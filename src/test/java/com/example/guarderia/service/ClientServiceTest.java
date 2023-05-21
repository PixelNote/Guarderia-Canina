package com.example.guarderia.service;

import com.example.guarderia.model.Client;
import com.example.guarderia.repository.ClientRepository;
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
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepository;

    @Test
    void given_nuevo_cliente_when_invoke_agregarCliente_then_return_saveCliente() {
        Client client = new Client(9, "Perez", "calle 2", "email@prueba.com");
        Mockito.when(clientRepository.save(any(Client.class)))
                .thenReturn(client);
        Client result = clientService.saveClient(client);
        Assertions.assertEquals(client, result);
        Mockito.verify(clientRepository).save(any());
    }

    @Test
    void when_obtenerClientes_then_return_lista_de_clientes() {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(10, "Andres", "Calle 1", "email@prueba.com");
        Client client2 = new Client(11, "Juan", "Calle 2", "email@prueba.com");
        clients.add(client1);
        clients.add(client2);

        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result= clientService.getClients();

        Assertions.assertEquals(clients, result);
        Mockito.verify(clientRepository).findAll();


    }

}
