package com.example.guarderia.controlador.DTO;
import com.example.guarderia.datos.Mascota;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
public class ClienteDTO {
    private Integer documento;
    private String nombre;
    private String direccion;
}