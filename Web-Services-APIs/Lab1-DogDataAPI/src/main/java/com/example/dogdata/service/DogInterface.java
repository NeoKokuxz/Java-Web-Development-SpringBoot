package com.example.dogdata.service;

import com.example.dogdata.entity.Dog;

import java.util.List;

public interface DogInterface {
    List<Dog> retrieveDogs();

    List<String> retrieveDogBreed();

    String retrieveDogBreedById(Long id);

    List<String> retrieveDogNames();
}
