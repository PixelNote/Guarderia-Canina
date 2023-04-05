package com.example.guarderia.controlador;

import com.example.guarderia.AbstractTest;
import com.example.guarderia.controlador.DTO.ClienteDTO;
import com.example.guarderia.controlador.DTO.MascotaDTO;
import com.example.guarderia.controlador.DTO.ReservaDTO;
import com.example.guarderia.datos.Cliente;
import com.example.guarderia.datos.Mascota;
import com.example.guarderia.datos.Reserva;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservaControladorTest extends AbstractTest {

    private static final String PATH_GUARDAR_RESERVA = "/reserva";
    private static final String PATH_CONSULTAR_RESERVAS = "/fecha?fecha=";
    private static final String PATH_CONSULTAR_HISTORIAL = "/documento?documento=";

    private static final String PATH_GUARDAR_CLIENTE = "/cliente";

    private final static String PATH_GUARDAR_MASCOTA = "/mascota";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_mascota_existe_when_invoke_guardarReserva_then_guarda_reserva(){
        ClienteDTO clienteDTO = new ClienteDTO(6,"Daniela", "calle 8");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Yiyo", 1);
        restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);
        ReservaDTO reservaDTO = new ReservaDTO("2022-05-23","Yiyo",6);
        ResponseEntity<Reserva> responseEntity = restTemplate
                .postForEntity(PATH_GUARDAR_RESERVA,reservaDTO, Reserva.class);

        System.out.println(responseEntity.getBody());

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }

    @Test
    public void given_fecha_when_invoke_consultarReservas_then_return_lista_de_mascotas(){

        String fecha = "2022-05-24";

        ClienteDTO clienteDTO = new ClienteDTO(7,"Sebas", "calle 9");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Satanas", 7);
        restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);
        ReservaDTO reservaDTO = new ReservaDTO("2022-05-24", "Satanas", 7);
        restTemplate.postForEntity(PATH_GUARDAR_RESERVA, reservaDTO, Reserva.class);

        ResponseEntity<Mascota[]> responseEntity = restTemplate
                .exchange(PATH_CONSULTAR_RESERVAS+fecha, HttpMethod.GET, null, Mascota[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

      //  Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).length);

    }

    @Test
    public void given_documento_when_invoke_consultarHistorial_then_return_lista_de_reservas(){

        int documento = 8;

        ClienteDTO clienteDTO = new ClienteDTO(8,"Nicolas", "calle 10");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Rebecca", 8);
        restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);
        ReservaDTO reservaDTO = new ReservaDTO("2022-05-25", "Rebecca", 8);
        restTemplate.postForEntity(PATH_GUARDAR_RESERVA, reservaDTO, Reserva.class);

        ResponseEntity<Reserva[]> responseEntity = restTemplate
                .exchange(PATH_CONSULTAR_HISTORIAL+documento, HttpMethod.GET, null, Reserva[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      //  Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).length);

    }

}
