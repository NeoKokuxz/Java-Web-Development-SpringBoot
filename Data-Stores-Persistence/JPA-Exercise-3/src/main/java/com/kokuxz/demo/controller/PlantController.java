package com.kokuxz.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kokuxz.demo.entity.Plant;
import com.kokuxz.demo.service.PlantService;
import com.kokuxz.demo.views.Views;
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
    @JsonView(Views.Public.class)
    public List<Plant> underPriceThan(@PathVariable BigDecimal price){
        return plantService.findPlantsBelowPrice(price);
    }
}
