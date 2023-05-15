package com.example.guarderia.controller;


import com.example.guarderia.AbstractTest;
import com.example.guarderia.controller.DTO.ClientDTO;
import com.example.guarderia.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.aspectj.util.LangUtil.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientControllerTest extends AbstractTest {

    private static final String PATH_GET_CLIENTS = "/clients";
    private static final String PATH_SAVE_CLIENT = "/client";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_clienteDTO_when_invoke_guardarCLiente_then_crear_cliente(){
        ClientDTO clientDTO = new ClientDTO(1,"Maria", "calle 2", "email@prueba.com");
        ResponseEntity<Client> responseEntity = restTemplate
                .postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        assertEquals(new Client(1,"Maria", "calle 2", "email@prueba.com"),responseEntity.getBody());
    }

    @Test
    public void give_lista_de_clientes_when_invoke_obtenerClientes_then_result_lista_de_clientes(){
        ClientDTO clientDTO = new ClientDTO(2,"Juan", "calle 3", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);

        ResponseEntity<Client[]> responseEntity = restTemplate
                .exchange(PATH_GET_CLIENTS, HttpMethod.GET,null, Client[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Client[] clients = responseEntity.getBody();
        Assertions.assertFalse(isEmpty(clients));

    }


}