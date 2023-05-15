package com.example.guarderia.service;

import com.example.guarderia.model.Client;
import com.example.guarderia.model.Pet;
import com.example.guarderia.repository.ClientRepository;
import com.example.guarderia.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @InjectMocks
    private PetService petService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private ClientRepository clientRepository;

    @Test
    void given_documentoCliente_excede_mascotas_when_invoke_agregarMascota_then_throw_RuntimeException() {

        int clientId = 1;
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Pinky", new Client(clientId, "Mariana", "calle 1", "email@prueba.com")));
        pets.add(new Pet("Luki", new Client(clientId, "Julian", "calle 2", "email@prueba.com")));
        Mockito.when(petRepository.findPetsByClient(clientRepository.getClientByDocument(clientId)))
                .thenReturn(pets);

        Assertions.assertThrows(RuntimeException.class, () ->
                petService.savePet("Rebe", clientId));

        Mockito.verify(petRepository).findPetsByClient(clientRepository.getClientByDocument(clientId));
    }

    @Test
    void given_nueva_mascota_when_agregarMascota_then_return_saveMascota() {

        int clientId = 1;
        Client client = new Client(clientId, "Santiago", "calle 3", "email@prueba.com");
        Pet pet = new Pet("Laika", clientRepository.getClientByDocument(clientId));
        Mockito.when(clientRepository.getClientByDocument(clientId)).thenReturn(client);
        Mockito.when(petRepository.save(any())).thenReturn(pet);

        Pet resultado = petService.savePet("Laika", clientId);
        Assertions.assertEquals(pet, resultado);

        Mockito.verify(petRepository).save(any());
    }

    @Test
    void given_busqueda_mascotas_when_obtenerMascotas_then_return_ListMascotas() {

        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Lulu", new Client(2, "Paola", "calle 12", "email@prueba.com")));
        pets.add(new Pet("Mati", new Client(3, "Laura", "calle 13", "email@prueba.com")));
        Mockito.when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.getPets();
        Assertions.assertEquals(pets, result);

        Mockito.verify(petRepository).findAll();
    }

    @Test
    void given_documentoCliente_when_obtenerMascotasCliente_then_return_ListMascotas() {

        int clientId = 1;
        Client client = new Client(clientId, "Jenny", "calle 4", "email@prueba.com");
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Yiyo", client));
        pets.add(new Pet("Mani", client));

        Mockito.when(clientRepository.getClientByDocument(clientId)).thenReturn(client);
        Mockito.when(petRepository.findPetsByClient(client)).thenReturn(pets);

        List<Pet> result = petService.getClientPets(clientId);
        Assertions.assertEquals(pets, result);

        Mockito.verify(petRepository).findPetsByClient(client);
    }

}
