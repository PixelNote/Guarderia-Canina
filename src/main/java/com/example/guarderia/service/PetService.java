package com.example.guarderia.service;

import com.example.guarderia.model.Pet;
import com.example.guarderia.repository.ClientRepository;
import com.example.guarderia.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService implements IPetService {

    private PetRepository petRepository;
    private ClientRepository clientRepository;

    public Pet savePet(String name, Integer id){

       if(petRepository.findPetsByClient(clientRepository.getClientByDocument(id)).size()>1){
            throw new RuntimeException("Excede el numero de mascotas registradas.");
        }

        return petRepository.save(new Pet(name,clientRepository.getClientByDocument(id)));

    }
    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    public List<Pet> getClientPets(Integer id) {
        return petRepository.findPetsByClient(clientRepository.getClientByDocument(id));
    }
}
