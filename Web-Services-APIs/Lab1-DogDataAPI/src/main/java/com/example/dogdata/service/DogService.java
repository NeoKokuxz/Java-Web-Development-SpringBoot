package com.example.dogdata.service;

import com.example.dogdata.entity.Dog;
import com.example.dogdata.repository.DogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService implements DogInterface {

    @Autowired
    DogRepo repo;

    public List<Dog> retrieveDogs(){
        return (List<Dog>) repo.findAll();
    }

    public List<String> retrieveDogBreed(){
        return repo.findAllBreed();
    }

//    public String retrieveDogBreedById(Long id) {
//        return repo.findBreedById(id);
//    }

    public List<String> retrieveDogNames(){
        return repo.findAllName();
    }

    public String retrieveDogBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(repo.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }

}
