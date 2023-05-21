package com.example.guarderia.service;

import com.example.guarderia.model.Booking;
import com.example.guarderia.model.Client;
import com.example.guarderia.model.Pet;
import com.example.guarderia.repository.BookingRepository;
import com.example.guarderia.repository.ClientRepository;
import com.example.guarderia.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private ClientRepository clientRepository;

    @Test
    void given_reserva_excede_reservas_diarias_when_invoke_agregarReserva_then_throw_RuntimeException() {
        LocalDate date = LocalDate.of(2023,5,1);
        LocalTime time = LocalTime.of(18,50);

        Mockito.when(bookingRepository.countByDate(date)).thenReturn(21);
        Assertions.assertThrows(RuntimeException.class, () ->
                bookingService.saveBooking(date, time, "Fany", 1));

        Mockito.verify(bookingRepository).countByDate(date);
    }

    @Test
    void given_mascota_no_existe_when_invoke_agregarReserva_then_throw_RuntimeException() {

        LocalDate date = LocalDate.of(2023,5,1);
        LocalTime time = LocalTime.of(18,50);
        String petName = "Fany";
        Client client = new Client(35, "Josefina", "Calle 1", "email@prueba.com");

        Mockito.when(clientRepository.getClientByDocument(35)).thenReturn(client);
        Mockito.when(petRepository.findByClientAndName(clientRepository.getClientByDocument(35),petName)).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () ->
                bookingService.saveBooking(date,time, petName, 35));

        Mockito.verify(petRepository).findByClientAndName(client,petName);
    }

    @Test
    void given_mascota_registrada_when_invoke_agregarReserva_then_throw_RuntimeException() {

        LocalDate date = LocalDate.of(2023,5,1);
        LocalTime time = LocalTime.of(18,50);
        String petName = "Lulu";
        Client client =  new Client(45, "Jorge", "Calle 1", "email@mail.com");
        Pet pet = new Pet(petName,client);
        Booking booking = new Booking(date,time,pet);
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);

        Mockito.when(clientRepository.getClientByDocument(45)).thenReturn(client);
        Mockito.when(petRepository.findByClientAndName(clientRepository.getClientByDocument(45),petName)).thenReturn(pet);

        Mockito.when(petRepository.findPetByName(petName)).thenReturn(pet);
        Mockito.when(bookingRepository.findBookingsByPetAndDate(pet,date)).thenReturn(bookingList);

        Assertions.assertThrows(RuntimeException.class, () ->
                bookingService.saveBooking(date,time, petName, 45));

        Mockito.verify(petRepository).findByClientAndName(client,petName);
        Mockito.verify(petRepository).findPetByName(petName);
        Mockito.verify(bookingRepository).findBookingsByPetAndDate(pet,date);


    }

    @Test
    void given_nueva_reserva_when_agregarReserva_then_return_saveReserva() {

        LocalDate date = LocalDate.of(2023,5,29);
        LocalTime time = LocalTime.of(18,50);
        String petName = "Bruno";
        int document = 1;
        Client client =  new Client(54, "Raul", "Calle 1", "email@mail.com");
        Pet pet = new Pet(petName, client);

        Booking booking = new Booking(date, time, pet);

        Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking result = bookingRepository.save(booking);

        Assertions.assertEquals(booking, result);

        Mockito.verify(bookingRepository).save(any());

    }

    @Test
    void given_date_when_invoke_getPetsByDate_then_pet_list() {

        LocalDate date = LocalDate.of(2023,5,29);
        LocalTime time = LocalTime.of(18,50);

        Integer id = 123;
        Client client = new Client(id, "Client 1", "Address 1", "email1@example.com");
        List<Pet> pets = Arrays.asList(
                new Pet("Pet 1",client ),
                new Pet("Pet 2", client)
        );
        List<Booking> bookings1 = new ArrayList<>();
        bookings1.add(new Booking(date, time, pets.get(0)));
        List<Booking> bookings2 = new ArrayList<>();
        bookings1.add(new Booking(date, time, pets.get(1)));

        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(bookings1);
        bookings.addAll(bookings2);

        Mockito.when(clientRepository.getClientByDocument(123)).thenReturn(client);
        Mockito.when(petRepository.findPetsByClient(clientRepository.getClientByDocument(id))).thenReturn(pets);
        Mockito.when(bookingRepository.findBookingsByPet(pets.get(0))).thenReturn(bookings1);
        Mockito.when(bookingRepository.findBookingsByPet(pets.get(1))).thenReturn(bookings2);

        List<Booking> result = new ArrayList<>();
        for(Pet petSearch : petRepository.findPetsByClient(clientRepository.getClientByDocument(id))){

            result.addAll(bookingRepository.findBookingsByPet(petSearch));
        }

        Assertions.assertEquals(bookings, result);

        Mockito.verify(petRepository).findPetsByClient(client);
        Mockito.verify(bookingRepository).findBookingsByPet(pets.get(0));




    }

    @Test
    void given_date_when_invoke_getPetsByDate_then_throw_RuntimeException() {

        LocalDate date = LocalDate.of(2023,5,29);
        LocalTime time = LocalTime.of(18,50);

        Integer id = 456;
        Client client = new Client(id, "Client 1", "Address 1", "email1@example.com");
        List<Pet> pets = Arrays.asList(
                new Pet("Pet 1",client ),
                new Pet("Pet 2", client)
        );


        Mockito.when(clientRepository.getClientByDocument(456)).thenReturn(client);
        Mockito.when(petRepository.findPetsByClient(clientRepository.getClientByDocument(id))).thenReturn(pets);
        Mockito.when(bookingRepository.findBookingsByPet(pets.get(0))).thenReturn(Collections.emptyList());

        Assertions.assertThrows(RuntimeException.class, () ->
                bookingService.getClientBookings(456));


        Mockito.verify(petRepository).findPetsByClient(client);
        Mockito.verify(bookingRepository).findBookingsByPet(pets.get(0));




    }




}