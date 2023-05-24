package com.example.guarderia.controller;

import com.example.guarderia.controller.DTO.PetDTO;
import com.example.guarderia.model.Pet;
import com.example.guarderia.service.IPetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://front:8080","http://localhost:8080"})
public class PetController {

    private IPetService petService;

    @PostMapping("/pet")
    public Pet savePet(@RequestBody PetDTO petDTO){
        return petService.savePet(petDTO.getName(), petDTO.getUserid());
    }

    @GetMapping("/pets/all")
    public List<Pet> getPets(){
        return petService.getPets();
    }

    @GetMapping("/pets")
    public List<Pet> getClientPets(@RequestParam("userid") Integer id){

        return petService.getClientPets(id);
    }

}
