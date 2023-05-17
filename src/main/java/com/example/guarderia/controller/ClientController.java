package com.example.guarderia.controller;
import com.example.guarderia.controller.DTO.ClientDTO;
import com.example.guarderia.model.Client;
import com.example.guarderia.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {
    private IClientService clientService;

    @PostMapping("/client")
    public Client saveClient(@RequestBody ClientDTO clientDTO){
        return clientService.saveClient(new Client(clientDTO.getDocument(),
                clientDTO.getName(),
                clientDTO.getAddress(),
                clientDTO.getEmail()
        ));
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientService.getClients();
    }

}




