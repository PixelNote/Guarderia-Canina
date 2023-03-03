package com.example.guarderia.repositorio;

import com.example.guarderia.datos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente,String> {

}
