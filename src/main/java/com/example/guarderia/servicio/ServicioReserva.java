package com.example.guarderia.servicio;
import com.example.guarderia.dato.Mascota;
import com.example.guarderia.dato.Reserva;
import com.example.guarderia.dato.Cliente;
import com.example.guarderia.repositorio.IClienteRepositorio;
import com.example.guarderia.repositorio.IMascotaRepositorio;
import com.example.guarderia.repositorio.IReservaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioReserva{
    private IReservaRepositorio reservaRepositorio;
    private IMascotaRepositorio mascotaRepositorio;
    private IClienteRepositorio clienteRepositorio;


    public Reserva agregarReserva(String fecha, String mascota, Integer documento){
        List<Mascota> mascotas = reservaRepositorio.findByFecha(fecha);


        if(reservaRepositorio.countByFecha(fecha)>20){
            throw new RuntimeException("Excede el numero de reservas por dia.");
        }
        if(mascotaRepositorio.findMascotaByNombre(mascota)==null){
            throw new RuntimeException("Mascota no existe.");
        }
        if(mascotas.size()>0){
            throw new RuntimeException("Mascota ya esta registrada en esta fecha.");
        }

        Reserva reserva = new Reserva(fecha, mascotaRepositorio.findMascotaByNombre(mascota));

        return reservaRepositorio.save(reserva);
    }
    public List<Mascota> consultarReservas(String fecha){
        return reservaRepositorio.findByFecha(fecha);
    }

    public List<Reserva> consultarHistorialCliente(Integer documento){
        Cliente cliente = clienteRepositorio.getClienteByDocumento(documento);

        List<Mascota> mascota = mascotaRepositorio.findMascotasByCliente(cliente);
        List<Reserva> reservas = new ArrayList<>();
        for(Mascota mascotas : mascota){

            reservas.addAll(reservaRepositorio.findByMascota(mascotas));
        }
        if(reservas.isEmpty()){
            throw new RuntimeException("No hay reservas disponibles.");
        }
        return reservas;
    }


}
