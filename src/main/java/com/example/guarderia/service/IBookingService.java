package com.example.guarderia.service;

import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IBookingService {

    Booking saveBooking(LocalDate dateTime, LocalTime time, String mascota, Integer id);

    List<Pet> getPetsByDate(LocalDate date);

    List<Booking> getClientBookings(Integer id);


}
