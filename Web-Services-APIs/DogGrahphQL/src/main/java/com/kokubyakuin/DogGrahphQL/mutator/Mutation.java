package com.kokubyakuin.DogGrahphQL.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.kokubyakuin.DogGrahphQL.entity.Dog;
import com.kokubyakuin.DogGrahphQL.exception.BreedNotFoundException;
import com.kokubyakuin.DogGrahphQL.exception.DogNotFoundException;
import com.kokubyakuin.DogGrahphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found!", id);
        }
    }

    public boolean deleteDogBreed(String breed) {

        boolean deleted = false;

        Iterable<Dog> allDogs = dogRepository.findAll();

        for (Dog d : allDogs) {
            if (d.getBreed().equals(breed)) {
                dogRepository.delete(d);
                deleted = true;
            }
        }

        if (deleted != true) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }

        return deleted;
    }
}
