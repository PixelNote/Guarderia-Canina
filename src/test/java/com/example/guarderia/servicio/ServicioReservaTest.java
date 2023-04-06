package com.example.guarderia.servicio;

import com.example.guarderia.dato.Cliente;
import com.example.guarderia.dato.Mascota;
import com.example.guarderia.dato.Reserva;
import com.example.guarderia.repositorio.IClienteRepositorio;
import com.example.guarderia.repositorio.IMascotaRepositorio;
import com.example.guarderia.repositorio.IReservaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServicioReservaTest {

    @InjectMocks
    private ServicioReserva servicioReserva;
    @Mock
    private IReservaRepositorio reservaRepositorio;
    @Mock
    private IMascotaRepositorio mascotaRepositorio;
    @Mock
    private IClienteRepositorio clienteRepositorio;


    @Test
    void given_reserva_excede_reservas_diarias_when_invoke_agregarReserva_then_throw_RuntimeException() {
        String fechaReserva = "2022-05-01";

        Mockito.when(reservaRepositorio.countByFecha(fechaReserva)).thenReturn(21);
        Assertions.assertThrows(RuntimeException.class, () ->
                servicioReserva.agregarReserva(fechaReserva, "Fany", 1));

        // Verificar que se llamaron los métodos en los repositorios
        Mockito.verify(reservaRepositorio).countByFecha(fechaReserva);
    }

    @Test
    void given_nombre_mascota_no_existe_when_invoke_agregarReserva_then_throw_RuntimeException() {

        String nombreMascota = "Fany";
        Mockito.when(mascotaRepositorio.findMascotaByNombre(nombreMascota)).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () ->
                servicioReserva.agregarReserva("2022-05-01", nombreMascota, 1));

        Mockito.verify(mascotaRepositorio).findMascotaByNombre(nombreMascota);
    }

    @Test
    void given_mascota_registrada_when_invoke_agregarReserva_then_throw_RuntimeException() {

        String fecha = "2023-04-04";
        String nombreMascota = "Bruno";
        Mascota mascota = new Mascota(nombreMascota, new Cliente(1, "Pepe", "Calle 1"));
        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(mascota);

        Mockito.when(mascotaRepositorio.findMascotaByNombre(nombreMascota)).thenReturn(mascota);
        Mockito.when(reservaRepositorio.findByFecha(fecha)).thenReturn(mascotas);

        Assertions.assertThrows(RuntimeException.class, () ->
                servicioReserva.agregarReserva(fecha, nombreMascota, 1));

        Mockito.verify(mascotaRepositorio).findMascotaByNombre(nombreMascota);
        Mockito.verify(reservaRepositorio).findByFecha(fecha);


    }

    @Test
    void given_nueva_reserva_when_agregarReserva_then_return_saveReserva() {

        String fecha = "2023-04-04";
        String nombreMascota = "Bruno";
        int documento = 1;
        Mascota mascota = new Mascota(nombreMascota, new Cliente(documento, "Pepe", "Calle 1"));
        Reserva reserva = new Reserva(fecha, mascota);

        Mockito.when(mascotaRepositorio.findMascotaByNombre(nombreMascota)).thenReturn(mascota);
        Mockito.when(reservaRepositorio.findByFecha(fecha)).thenReturn(new ArrayList<>());
        Mockito.when(reservaRepositorio.countByFecha(fecha)).thenReturn(20);
        Mockito.when(reservaRepositorio.save(any())).thenReturn(reserva);

        Reserva resultado = servicioReserva.agregarReserva(fecha, nombreMascota, documento);

        Assertions.assertEquals(reserva, resultado);

        Mockito.verify(reservaRepositorio).findByFecha(fecha);
        Mockito.verify(reservaRepositorio).countByFecha(fecha);
        Mockito.verify(reservaRepositorio).save(any());

    }

    @Test
    void given_documentoCLiente_when_invoke_consultarHistorialCliente_then_return_reservas() {

        int documentoCliente = 1;
        String fecha = "2023-04-04";
        Cliente cliente = new Cliente(documentoCliente, "Pepe", "calle 3");
        Mascota mascota = new Mascota("Pancho", cliente);
        List<Mascota> mascotas = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        mascotas.add(mascota);
        reservas.add(new Reserva(fecha, mascota));
        Mockito.when(clienteRepositorio.getClienteByDocumento(documentoCliente)).thenReturn(cliente);
        Mockito.when(mascotaRepositorio.findMascotasByCliente(cliente)).thenReturn(mascotas);
        Mockito.when(reservaRepositorio.findByMascota(mascota)).thenReturn(reservas);

        List<Reserva> resultado = servicioReserva.consultarHistorialCliente(documentoCliente);

        Assertions.assertEquals(reservas, resultado);

        Mockito.verify(clienteRepositorio).getClienteByDocumento(documentoCliente);
        Mockito.verify(mascotaRepositorio).findMascotasByCliente(cliente);
        Mockito.verify(reservaRepositorio).findByMascota(mascota);

    }

    @Test
    void given_documentoCLiente_when_invoke_consultarHistorialCliente_then_throw_RuntimeException() {

        int documentoCliente = 1;
        String fecha = "2023-04-04";
        Cliente cliente = new Cliente(documentoCliente, "Pepe", "calle 3");
        Mascota mascota = new Mascota("Pancho", cliente);
        List<Mascota> mascotas = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        mascotas.add(mascota);
        reservas.add(new Reserva(fecha, mascota));
        Mockito.when(clienteRepositorio.getClienteByDocumento(documentoCliente)).thenReturn(cliente);
        Mockito.when(mascotaRepositorio.findMascotasByCliente(cliente)).thenReturn(mascotas);
        Mockito.when(reservaRepositorio.findByMascota(mascota)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(RuntimeException.class, () ->
                servicioReserva.consultarHistorialCliente(documentoCliente));

        Mockito.verify(clienteRepositorio).getClienteByDocumento(documentoCliente);
        Mockito.verify(mascotaRepositorio).findMascotasByCliente(cliente);
        Mockito.verify(reservaRepositorio).findByMascota(mascota);

    }


}
