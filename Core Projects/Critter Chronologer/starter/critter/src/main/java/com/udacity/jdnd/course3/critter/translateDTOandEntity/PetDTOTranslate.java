package com.udacity.jdnd.course3.critter.translateDTOandEntity;

import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.customer.entity.Customer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PetDTOTranslate {
    private static Pet convertPetDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        Customer customer = new Customer();
        customer.setId(petDTO.getOwnerId());
        pet.setCustomer(customer);

        return pet;
    }

    private static PetDTO convertEntityToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private static List<PetDTO> convertEntityListToPetDTOList(List<Pet> petList) {
        List<PetDTO> petDTOList = new ArrayList<>();

        petList.forEach(pet -> petDTOList.add(convertEntityToPetDTO(pet)));
        return petDTOList;
    }
}
