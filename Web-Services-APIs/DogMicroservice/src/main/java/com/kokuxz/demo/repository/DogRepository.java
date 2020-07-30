package com.kokuxz.demo.repository;

import com.kokuxz.demo.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
