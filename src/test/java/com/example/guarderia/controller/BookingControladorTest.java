package com.example.guarderia.controller;

import com.example.guarderia.AbstractTest;
import com.example.guarderia.controller.DTO.ClientDTO;
import com.example.guarderia.controller.DTO.PetDTO;
import com.example.guarderia.controller.DTO.BookingDTO;
import com.example.guarderia.model.Client;
import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingControladorTest extends AbstractTest {

    private static final String PATH_SAVE_RESERVATION = "/reservation";
    private static final String PATH_GET_RESERVATIONS = "/reservation/pets?date=";
    private static final String PATH_GET_CLIENT_RESERVATIONS = "/reservation/id?userid=";
    private static final String PATH_SAVE_CLIENT = "/client";
    private final static String PATH_SAVE_PET = "/pet";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_mascota_existe_when_invoke_guardarReserva_then_guarda_reserva(){
        ClientDTO clientDTO = new ClientDTO(6,"Daniela", "calle 8", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Yiyo", 1);
        restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);
        BookingDTO bookingDTO = new BookingDTO(LocalDateTime.of(2023,5,20,14,30),"Yiyo",6);
        ResponseEntity<Booking> responseEntity = restTemplate
                .postForEntity(PATH_SAVE_RESERVATION, bookingDTO, Booking.class);

        System.out.println(responseEntity.getBody());

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }

    @Test
    public void given_fecha_when_invoke_consultarReservas_then_return_lista_de_mascotas(){

        String fecha = "2022-05-24";

        ClientDTO clientDTO = new ClientDTO(7,"Sebas", "calle 9", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Satanas", 7);
        restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);
        BookingDTO bookingDTO = new BookingDTO(LocalDateTime.of(2023,5,20,14,30), "Satanas", 7);
        restTemplate.postForEntity(PATH_SAVE_RESERVATION, bookingDTO, Booking.class);

        ResponseEntity<Pet[]> responseEntity = restTemplate
                .exchange(PATH_GET_RESERVATIONS +fecha, HttpMethod.GET, null, Pet[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void given_documento_when_invoke_consultarHistorial_then_return_lista_de_reservas(){

        int documento = 8;

        ClientDTO clientDTO = new ClientDTO(8,"Nicolas", "calle 10", "email@prueba.com");
        restTemplate.postForEntity(PATH_SAVE_CLIENT, clientDTO, Client.class);
        PetDTO petDTO = new PetDTO("Rebecca", 8);
        restTemplate.postForEntity(PATH_SAVE_PET, petDTO, Pet.class);
        BookingDTO bookingDTO = new BookingDTO(LocalDateTime.of(2023,5,20,14,30), "Rebecca", 8);
        restTemplate.postForEntity(PATH_SAVE_RESERVATION, bookingDTO, Booking.class);

        ResponseEntity<Booking[]> responseEntity = restTemplate
                .exchange(PATH_GET_CLIENT_RESERVATIONS +documento, HttpMethod.GET, null, Booking[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}
