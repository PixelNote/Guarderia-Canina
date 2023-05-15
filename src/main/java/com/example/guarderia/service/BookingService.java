package com.example.guarderia.service;
import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;
import com.example.guarderia.repository.ClientRepository;
import com.example.guarderia.repository.PetRepository;
import com.example.guarderia.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService {
    private BookingRepository bookingRepository;
    private PetRepository petRepository;
    private ClientRepository clientRepository;


    public Booking saveBooking(LocalDateTime date, String mascota, Integer id){

        List<Pet> pets = bookingRepository.findByDate(date);

        if(bookingRepository.countByDate(date)>20){
            throw new RuntimeException("Excede el numero de reservas por dia.");
        }
        if(petRepository.findPetsByClient(clientRepository.getClientByDocument(id))==null){
            throw new RuntimeException("Mascota no existe.");
        }
        if(pets.size()>0){
            throw new RuntimeException("Mascota ya esta registrada en esta fecha.");
        }
        return bookingRepository.save(new Booking(date, petRepository.findByClientAndName(clientRepository.getClientByDocument(id),mascota)));

    }
    public List<Pet> getPetsByDate(LocalDateTime date){
        return bookingRepository.findByDate(date);
    }

    public List<Booking> getClientBookings(Integer id){

        List<Booking> bookings = new ArrayList<>();
        for(Pet petSearch : petRepository.findPetsByClient(clientRepository.getClientByDocument(id))){

            bookings.addAll(bookingRepository.findBookingsByPet(petSearch));
        }
        if(bookings.isEmpty()){
            throw new RuntimeException("No hay reservas disponibles.");
        }
        return bookings;
    }


}
