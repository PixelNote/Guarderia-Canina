package com.example.guarderia.controller;
import com.example.guarderia.controller.DTO.BookingDTO;
import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;
import com.example.guarderia.service.IBookingService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class BookingController {

    private IBookingService reservationService;

    @PostMapping("/reservation")
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO){

        return reservationService.saveBooking(bookingDTO.getDate(),
                bookingDTO.getPet(),
                bookingDTO.getDocument());
    }


    @GetMapping(path = "/reservation/pets")
    public List<Pet> getPetsByDate(@RequestParam("date") String date){

        return reservationService.getPetsByDate(date);
    }


    @GetMapping(path = "/reservation/id")
    public List<Booking> getClientBookings(@RequestParam("userid") Integer id){
       return reservationService.getClientBookings(id);
    }
}
