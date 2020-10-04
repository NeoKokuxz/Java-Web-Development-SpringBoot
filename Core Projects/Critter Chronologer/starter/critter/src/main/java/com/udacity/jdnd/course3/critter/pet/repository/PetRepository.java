package com.udacity.jdnd.course3.critter.pet.repository;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
//    @Query("SELECT p from Pet p where p.customer = :customer")
//    List<Pet> findAllByCustomer(Customer customer);
//
//    @Query("SELECT p from Pet p where p.name = :name")
//    Pet findPetByName(String name);

    List<Pet> getPetsByCustomerId(Long id);

    @Query("select s from Pet s where pet_id = :id")
    Pet getPetByPetId(Long id);
}
