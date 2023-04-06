package com.example.guarderia.repositorio;


import com.example.guarderia.dato.Cliente;
import com.example.guarderia.dato.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMascotaRepositorio extends JpaRepository<Mascota,Integer> {

    Mascota findAllByCliente(Cliente cliente);

    Mascota findMascotaByNombre(String nombre);

    List<Mascota> findMascotasByCliente(Cliente cliente);
}