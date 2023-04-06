package com.example.guarderia.controlador.DTO;
import lombok.*;

@AllArgsConstructor
@Data
public class ClienteDTO {
    private Integer documento;
    private String nombre;
    private String direccion;
}