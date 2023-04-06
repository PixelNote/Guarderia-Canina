package com.example.guarderia.servicio;

import com.example.guarderia.dato.Cliente;
import com.example.guarderia.dato.Mascota;
import com.example.guarderia.repositorio.IClienteRepositorio;
import com.example.guarderia.repositorio.IMascotaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServicioMascotaTest {

    @InjectMocks
    private ServicioMascota servicioMascota;
    @Mock
    private IMascotaRepositorio mascotaRepositorio;
    @Mock
    private IClienteRepositorio clienteRepositorio;

    @Test
    void given_documentoCliente_excede_mascotas_when_invoke_agregarMascota_then_throw_RuntimeException() {

        int documentoCliente = 1;
        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Pinky", new Cliente(documentoCliente, "Mariana", "calle 1")));
        mascotas.add(new Mascota("Luki", new Cliente(documentoCliente, "Julian", "calle 2")));
        Mockito.when(mascotaRepositorio.findMascotasByCliente(clienteRepositorio.getClienteByDocumento(documentoCliente)))
                .thenReturn(mascotas);

        Assertions.assertThrows(RuntimeException.class, () ->
                servicioMascota.agregarMascota("Rebe", documentoCliente));

        Mockito.verify(mascotaRepositorio).findMascotasByCliente(clienteRepositorio.getClienteByDocumento(documentoCliente));
    }

    @Test
    void given_nueva_mascota_when_agregarMascota_then_return_saveMascota() {

        int documentoCliente = 1;
        Cliente cliente = new Cliente(documentoCliente, "Santiago", "calle 3");
        Mascota mascota = new Mascota("Laika", clienteRepositorio.getClienteByDocumento(documentoCliente));
        Mockito.when(clienteRepositorio.getClienteByDocumento(documentoCliente)).thenReturn(cliente);
        Mockito.when(mascotaRepositorio.save(any())).thenReturn(mascota);

        Mascota resultado = servicioMascota.agregarMascota("Laika", documentoCliente);
        Assertions.assertEquals(mascota, resultado);

        Mockito.verify(mascotaRepositorio).save(any());
    }

    @Test
    void given_busqueda_mascotas_when_obtenerMascotas_then_return_ListMascotas() {

        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Lulu", new Cliente(2, "Paola", "calle 12")));
        mascotas.add(new Mascota("Mati", new Cliente(3, "Laura", "calle 13")));
        Mockito.when(mascotaRepositorio.findAll()).thenReturn(mascotas);

        List<Mascota> resultado = servicioMascota.obtenerMascotas();
        Assertions.assertEquals(mascotas, resultado);

        Mockito.verify(mascotaRepositorio).findAll();
    }

    @Test
    void given_documentoCliente_when_obtenerMascotasCliente_then_return_ListMascotas() {

        int documentoCliente = 1;
        Cliente cliente = new Cliente(documentoCliente, "Jenny", "calle 4");
        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Yiyo", cliente));
        mascotas.add(new Mascota("Mani", cliente));

        Mockito.when(clienteRepositorio.getClienteByDocumento(documentoCliente)).thenReturn(cliente);
        Mockito.when(mascotaRepositorio.findMascotasByCliente(cliente)).thenReturn(mascotas);

        List<Mascota> resultado = servicioMascota.obtenerMascotasCliente(documentoCliente);
        Assertions.assertEquals(mascotas, resultado);

        Mockito.verify(mascotaRepositorio).findMascotasByCliente(cliente);
    }

}
