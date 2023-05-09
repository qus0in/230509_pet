package io.playdata.pet.service;

import io.playdata.pet.model.Pet;
import io.playdata.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired // 자동불러오기
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    public Pet getPet(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid pet Id:" + id)
        );
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
