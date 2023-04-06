package com.example.guarderia.repositorio;

import com.example.guarderia.dato.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente,Integer> {

    Cliente getClienteByDocumento(Integer documento);




}
