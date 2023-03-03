package com.example.guarderia.datos;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserva;
    @Column(nullable = false)
    private String fecha;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String mascota;

}
