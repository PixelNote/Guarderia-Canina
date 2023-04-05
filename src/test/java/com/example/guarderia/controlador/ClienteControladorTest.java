package com.example.guarderia.controlador;


import com.example.guarderia.AbstractTest;
import com.example.guarderia.controlador.DTO.ClienteDTO;
import com.example.guarderia.datos.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.aspectj.util.LangUtil.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteControladorTest extends AbstractTest {

    private static final String PATH_OBTENER_CLIENTES = "/clientes";
    private static final String PATH_GUARDAR_CLIENTE = "/cliente";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_clienteDTO_when_invoke_guardarCLiente_then_crear_cliente(){
        ClienteDTO clienteDTO = new ClienteDTO(1,"Maria", "calle 2");
        ResponseEntity<Cliente> responseEntity = restTemplate
                .postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);
        assertEquals(new Cliente(1,"Maria", "calle 2"),responseEntity.getBody());
    }

    @Test
    public void give_lista_de_clientes_when_invoke_obtenerClientes_then_result_lista_de_clientes(){
        ClienteDTO clienteDTO = new ClienteDTO(2,"Juan", "calle 3");
        restTemplate.postForEntity(PATH_GUARDAR_CLIENTE, clienteDTO, Cliente.class);

        ResponseEntity<Cliente[]> responseEntity = restTemplate
                .exchange(PATH_OBTENER_CLIENTES, HttpMethod.GET,null, Cliente[].class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Cliente[] clientes = responseEntity.getBody();
        Assertions.assertFalse(isEmpty(clientes));

    }


}