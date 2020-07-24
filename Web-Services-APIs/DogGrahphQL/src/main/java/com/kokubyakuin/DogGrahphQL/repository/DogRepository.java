package com.kokubyakuin.DogGrahphQL.repository;

import com.kokubyakuin.DogGrahphQL.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
