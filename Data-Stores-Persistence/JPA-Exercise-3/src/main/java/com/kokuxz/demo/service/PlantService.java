package com.kokuxz.demo.service;

import com.kokuxz.demo.entity.Plant;
import com.kokuxz.demo.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Long savePlant(Plant plant){
       return plantRepository.save(plant).getId();
    }

    public Boolean delivered(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price){
        return plantRepository.findByPriceLessThan(price);
    }
}
