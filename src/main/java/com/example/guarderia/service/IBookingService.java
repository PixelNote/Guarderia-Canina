package com.example.guarderia.service;

import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingService {

    Booking saveBooking(LocalDateTime dateTime, String mascota, Integer id);

    List<Pet> getPetsByDate(LocalDateTime date);

    List<Booking> getClientBookings(Integer id);


}
