package com.example.guarderia.controlador.DTO;
import lombok.*;
@AllArgsConstructor
@Data
public class ClienteDTO {
    private String documento;
    private String nombre;
    private String direccion;
    private String mascota;
}