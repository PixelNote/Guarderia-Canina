package com.example.guarderia.servicio;

import com.example.guarderia.datos.Reserva;
import com.example.guarderia.repositorio.IReservaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioReserva{
    private IReservaRepositorio reservaRepositorio;

    public Reserva agregarReserva(Reserva reserva){

        if(reservaRepositorio.countByFecha(reserva.getFecha())>20){
            throw new RuntimeException("Excede el numero de reservas por dia.");
        }
        return reservaRepositorio.save(reserva);
    }
    public List<Reserva> consultarReservas(String fecha){
        return reservaRepositorio.findAllByFecha(fecha);
    }

    public List<Reserva> consultarHistorialCliente(String documento){
        return reservaRepositorio.findAllByDocumento(documento);
    }


}
