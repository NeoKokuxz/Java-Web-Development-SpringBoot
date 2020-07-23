package com.example.dogdata.service;

import com.example.dogdata.entity.Dog;
import com.example.dogdata.repository.DogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    @Autowired
    DogRepo repo;

    public List<Dog> retrieveDogs(){
        return (List<Dog>) repo.findAll();
    }

    public List<String> retrieveDogBreed(){
        return repo.findAllBreed();
    }

    public String retrieveBreedById(Long id){
        return repo.findBreedById(id);
    }

    public List<String> retrieveDogNames(){
        return repo.findAllName();
    }
}
