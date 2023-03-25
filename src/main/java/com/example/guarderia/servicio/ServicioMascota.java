package com.example.guarderia.servicio;

import com.example.guarderia.datos.Cliente;
import com.example.guarderia.datos.Mascota;
import com.example.guarderia.repositorio.IClienteRepositorio;
import com.example.guarderia.repositorio.IMascotaRepositorio;
import com.example.guarderia.repositorio.IReservaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioMascota {
    private IMascotaRepositorio mascotaRepositorio;
    private IClienteRepositorio clienteRepositorio;

    public Mascota agregarMascota(String nombre, Integer documento){


       if(mascotaRepositorio.findMascotasByCliente(clienteRepositorio.getClienteByDocumento(documento)).size()>1){
            throw new RuntimeException("Excede el numero de mascotas registradas.");
        }

        Mascota mascota = new Mascota(nombre,clienteRepositorio.getClienteByDocumento(documento));
        return mascotaRepositorio.save(mascota);
    }
    public List<Mascota> obtenerMascotas(){
        return mascotaRepositorio.findAll();
    }

    public List<Mascota> obtenerMascotasCliente(Integer documento) {
        Cliente cliente = clienteRepositorio.getClienteByDocumento(documento);
        return mascotaRepositorio.findMascotasByCliente(cliente);
    }
}
