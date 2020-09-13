package com.kokuxz.demo.controller;

import com.kokuxz.demo.entity.Plant;
import com.kokuxz.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id){
        return plantService.delivered(id);
    }

    @GetMapping("/under-price/{price}")
    public List<Plant> underPriceThan(@PathVariable BigDecimal price){
        return plantService.findPlantsBelowPrice(price);
    }
}
