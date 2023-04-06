package com.example.guarderia.controlador;
import com.example.guarderia.controlador.DTO.ClienteDTO;
import com.example.guarderia.dato.Cliente;
import com.example.guarderia.servicio.ServicioCliente;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClienteControlador {
    private ServicioCliente servicio;

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        return servicio.obtenerClientes();
    }


    @PostMapping("/cliente")
    public Cliente guardarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO.getDocumento(),
                clienteDTO.getNombre(),
                clienteDTO.getDireccion()
                );
        return servicio.agregarCliente(cliente);
    }
}




