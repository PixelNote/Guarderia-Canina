package com.example.guarderia.repositorio;
import com.example.guarderia.dato.Mascota;
import com.example.guarderia.dato.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservaRepositorio extends JpaRepository<Reserva,Integer> {

    Integer countByFecha(String fecha);

    @Query("SELECT DISTINCT r.mascota FROM Reserva r WHERE r.fecha = :fecha")
    List<Mascota> findByFecha(@Param("fecha") String fecha);

    List<Reserva> findByMascota(Mascota mascotas);
}
