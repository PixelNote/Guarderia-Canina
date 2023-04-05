package com.example.guarderia.controlador;


import com.example.guarderia.AbstractTest;
import com.example.guarderia.controlador.DTO.ClienteDTO;
import com.example.guarderia.controlador.DTO.MascotaDTO;
import com.example.guarderia.datos.Cliente;
import com.example.guarderia.datos.Mascota;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MascotaControladorTest extends AbstractTest {

    private static final String PATH_GUARDAR_CLIENTE = "/cliente";

    private final static String PATH_GUARDAR_MASCOTA = "/mascota";
    private final static String PATH_OBTENER_MASCOTAS = "/mascotas/todas";
    private final static String PATH_OBTENER_MASCOTAS_CLIENTE = "/mascotas?documento=";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_usuario_existe_when_invoke_guardarMascota_then_guarda_mascota(){
        ClienteDTO clienteDTO = new ClienteDTO(3,"Yeyson", "calle 4");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Zeus", 3);
        ResponseEntity<Mascota> responseEntity = restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new Mascota(1,"Zeus",new Cliente(3,"Yeyson", "calle 4")),responseEntity.getBody());
    }

    @Test
    public void given_lista_de_mascotas_when_invoke_obtenerMascotas_then_result_lista_de_mascotas(){
        ClienteDTO clienteDTO = new ClienteDTO(4,"Andres", "calle 5");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Oreo", 4);
        restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);



        ResponseEntity<Mascota[]> responseEntity = restTemplate
                .exchange(PATH_OBTENER_MASCOTAS, HttpMethod.GET,null, Mascota[].class);


        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
     //   Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).length);

    }

    @Test
    public void given_documento_when_invoke_obtenerMascotasCliente_then_result_lista_de_mascotas(){
        int documento = 1;
        ClienteDTO clienteDTO = new ClienteDTO(5,"Pedro", "calle 6");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        MascotaDTO mascotaDTO = new MascotaDTO("Pablo", 5);
        restTemplate.postForEntity(PATH_GUARDAR_MASCOTA, mascotaDTO, Mascota.class);

        ResponseEntity<Mascota[]> responseEntity = restTemplate
                .exchange(PATH_OBTENER_MASCOTAS_CLIENTE+documento,
                        HttpMethod.GET,null, Mascota[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

     //   Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).length);

    }

}