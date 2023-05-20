package com.example.guarderia.repository;
import com.example.guarderia.model.Pet;
import com.example.guarderia.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    Integer countByDate(LocalDate date);

    @Query("SELECT DISTINCT r.pet FROM Booking r WHERE r.date = :date")
    List<Pet> findByDate(@Param("date") LocalDate date);

    List<Booking> findBookingsByDateAndDelivered(LocalDate date, String delivered);

    List<Booking> findBookingsByPet(Pet pet);

    List<Booking> findBookingsByPetAndDate(Pet pet, LocalDate date);
}
