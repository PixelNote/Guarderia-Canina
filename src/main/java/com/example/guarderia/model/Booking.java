package com.example.guarderia.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private LocalTime time;
    @ManyToOne
    private Pet pet;

    private String delivered;

    public Booking(LocalDate date, LocalTime time, Pet pet) {
        this.date = date;
        this.time = time;
        this.pet = pet;
        this.delivered = "false";
    }
}
