package com.example.guarderia.controller.DTO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingDTO {
    private LocalDateTime date;
    private String pet;
    private Integer document;

}

