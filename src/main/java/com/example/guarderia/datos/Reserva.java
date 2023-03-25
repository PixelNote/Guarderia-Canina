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
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    public Reserva(String fecha, Mascota mascota) {
        this.fecha = fecha;
        this.mascota = mascota;
    }
}
