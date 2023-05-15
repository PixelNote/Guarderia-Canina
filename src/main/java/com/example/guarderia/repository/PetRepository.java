package com.example.guarderia.repository;


import com.example.guarderia.model.Client;
import com.example.guarderia.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {
    List<Pet> findPetsByClient(Client client);

    Pet findByClientAndName(Client client, String name);
}