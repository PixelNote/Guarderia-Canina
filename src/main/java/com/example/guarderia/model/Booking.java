package com.example.guarderia.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    @ManyToOne
    private Pet pet;

    public Booking(LocalDateTime date, Pet pet) {
        this.date = date;
        this.pet = pet;
    }

}
