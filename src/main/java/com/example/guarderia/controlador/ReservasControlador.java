package com.example.guarderia.controlador;
import com.example.guarderia.controlador.DTO.ReservaDTO;
import com.example.guarderia.datos.Reserva;
import com.example.guarderia.servicio.ServicioReserva;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservasControlador {

    private ServicioReserva servicio;
    @PostMapping("/reserva")
    public Reserva guardarReserva(@RequestBody ReservaDTO reservaDTO){
        Reserva reserva = new Reserva(null,
                reservaDTO.getFecha(),
                reservaDTO.getDocumento(),
                reservaDTO.getMascota());
        return servicio.agregarReserva(reserva);
    }
    @GetMapping(path = "/fecha/{fecha}")
    public List<Reserva> consultarReservas(@PathVariable String fecha){
        return servicio.consultarReservas(fecha);
    }

    @GetMapping(path = "/documento/{documento}")
    public List<Reserva> consultarHistorial(@PathVariable String documento){
        return servicio.consultarHistorialCliente(documento);
    }
}
