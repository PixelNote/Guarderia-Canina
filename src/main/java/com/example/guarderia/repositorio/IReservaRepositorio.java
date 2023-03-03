package com.example.guarderia.repositorio;
import com.example.guarderia.datos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepositorio extends JpaRepository<Reserva,String> {
    List<Reserva> findAllByFecha(String fecha);
    List<Reserva> findAllByDocumento(String documento);
    Integer countByFecha(String fecha);



}
