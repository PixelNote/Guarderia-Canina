package com.example.guarderia.controller.DTO;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingDTO {
    private LocalDate date;
    private LocalTime time;
    private String pet;
    private Integer document;

}

