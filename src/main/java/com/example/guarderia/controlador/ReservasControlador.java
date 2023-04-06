package com.example.guarderia.controlador;
import com.example.guarderia.controlador.DTO.ReservaDTO;
import com.example.guarderia.dato.Mascota;
import com.example.guarderia.dato.Reserva;
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


        return servicio.agregarReserva(reservaDTO.getFecha(),
                reservaDTO.getMascota(),
                reservaDTO.getDocumento());
    }


    @GetMapping(path = "/fecha")
    public List<Mascota> consultarReservas(@RequestParam("fecha") String fecha){

        return servicio.consultarReservas(fecha);
    }


    @GetMapping(path = "/documento")
    public List<Reserva> consultarHistorial(@RequestParam("documento") Integer documento){
       return servicio.consultarHistorialCliente(documento);
    }
}
