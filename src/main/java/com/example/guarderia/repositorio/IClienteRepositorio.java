package com.example.guarderia.repositorio;

import com.example.guarderia.datos.Cliente;
import com.example.guarderia.datos.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente,Integer> {

    Cliente getClienteByDocumento(Integer documento);




}
