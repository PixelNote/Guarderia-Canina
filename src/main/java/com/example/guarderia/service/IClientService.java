package com.example.guarderia.service;

import com.example.guarderia.model.Client;

import java.util.List;

public interface IClientService {

    Client saveClient(Client client);

    List<Client> getClients();


}
