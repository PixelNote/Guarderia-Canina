package com.example.guarderia.controlador;

import com.example.guarderia.controlador.DTO.ClienteDTO;
import com.example.guarderia.controlador.DTO.MascotaDTO;
import com.example.guarderia.datos.Cliente;
import com.example.guarderia.datos.Mascota;
import com.example.guarderia.servicio.ServicioMascota;
import com.example.guarderia.servicio.ServicioReserva;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MascotaControlador {
    private ServicioMascota servicio;

    @PostMapping("/mascota")
    public Mascota guardarMascota(@RequestBody MascotaDTO mascotaDTO){
        return servicio.agregarMascota(mascotaDTO.getNombre(), mascotaDTO.getDocumento());
    }

    @GetMapping("/mascotas/todas")
    public List<Mascota> obtenerMascotas(){
        return servicio.obtenerMascotas();
    }

    @GetMapping("/mascotas")
    public List<Mascota> obtenerMascotasCliente(@RequestParam("documento") Integer documento){

        return servicio.obtenerMascotasCliente(documento);
    }





}
