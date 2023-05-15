package com.example.guarderia.service;

import com.example.guarderia.model.Pet;

import java.util.List;

public interface IPetService {

    Pet savePet(String nombre, Integer id);

    List<Pet> getPets();

    List<Pet> getClientPets(Integer id);


}
