package com.example.guarderia.controlador.DTO;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaDTO {
    private Integer reserva;
    private String fecha;
    private String documento;
    private String mascota;
}