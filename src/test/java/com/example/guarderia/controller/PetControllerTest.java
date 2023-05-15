package com.example.guarderia.controller;


import com.example.guarderia.AbstractTest;
import com.example.guarderia.controller.DTO.ClientDTO;
import com.example.guarderia.controller.DTO.PetDTO;
import com.example.guarderia.model.Client;
import com.example.guarderia.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetControllerTest extends AbstractTest {

    private static final String PATH_SAVE_CLIENT = "/client";
    private final static String PATH_SAVE_PET = "/pet";
    private final static String PATH_GET_PETS = "/pets/all";
    private final static String PATH_GET_CLIENT_PETS = "/pets?userid=";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_usuario_existe_when_invoke_guardarMascota_then_guarda_mascota(){
        ClientDTO clientDTO = new ClientDTO(3,"Yeyson", "calle 4", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Zeus", 3);
        ResponseEntity<Pet> responseEntity = restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new Pet(1,"Zeus",new Client(3,"Yeyson", "calle 4", "email@prueba.com")),responseEntity.getBody());
    }

    @Test
    public void given_lista_de_mascotas_when_invoke_obtenerMascotas_then_result_lista_de_mascotas(){
        ClientDTO clientDTO = new ClientDTO(4,"Andres", "calle 5", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Oreo", 4);
        restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);



        ResponseEntity<Pet[]> responseEntity = restTemplate
                .exchange(PATH_GET_PETS, HttpMethod.GET,null, Pet[].class);


        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void given_documento_when_invoke_obtenerMascotasCliente_then_result_lista_de_mascotas(){
        int documento = 1;
        ClientDTO clientDTO = new ClientDTO(5,"Pedro", "calle 6", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Pablo", 5);
        restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);

        ResponseEntity<Pet[]> responseEntity = restTemplate
                .exchange(PATH_GET_CLIENT_PETS +documento,
                        HttpMethod.GET,null, Pet[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}