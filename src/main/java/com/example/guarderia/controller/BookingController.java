package com.example.guarderia.controller;
import com.example.guarderia.controller.DTO.BookingDTO;
import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;
import com.example.guarderia.service.IBookingService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://front:8080"})
public class BookingController {

    private IBookingService reservationService;

    @PostMapping("/booking")
    public Booking saveBooking(@RequestBody BookingDTO bookingDTO){

        return reservationService.saveBooking(bookingDTO.getDate(),
                bookingDTO.getTime(),
                bookingDTO.getPet(),
                bookingDTO.getDocument());
    }


    @GetMapping(path = "/booking/pets")
    public List<Pet> getPetsByDate(@RequestParam("date") LocalDate date){

        return reservationService.getPetsByDate(date);
    }


    @GetMapping(path = "/booking/id")
    public List<Booking> getClientBookings(@RequestParam("userid") Integer id){
       return reservationService.getClientBookings(id);
    }
}
