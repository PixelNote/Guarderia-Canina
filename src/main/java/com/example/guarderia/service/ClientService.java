package com.example.guarderia.service;

import com.example.guarderia.model.Client;
import com.example.guarderia.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private ClientRepository clientRepository;

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
    public List<Client> getClients(){ return clientRepository.findAll(); }

}
