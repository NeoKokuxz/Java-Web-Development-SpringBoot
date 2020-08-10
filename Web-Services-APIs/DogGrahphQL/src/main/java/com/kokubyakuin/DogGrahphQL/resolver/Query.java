package com.kokubyakuin.DogGrahphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.kokubyakuin.DogGrahphQL.entity.Dog;
import com.kokubyakuin.DogGrahphQL.exception.DogNotFoundException;
import com.kokubyakuin.DogGrahphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findDogBreeds() {
        return dogRepository.findAll();
    }

    public Dog findDogBreedById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
//            Dog dog = optionalDog.get();
//            return dog;
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }

    public Iterable<Dog> findAllDogNames() {
        return dogRepository.findAll();
    }
}
