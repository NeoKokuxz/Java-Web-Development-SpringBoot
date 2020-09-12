package com.kokuxz.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kokuxz.demo.dtos.PlantDTO;
import com.kokuxz.demo.entity.Plant;
import com.kokuxz.demo.jsonview.Views;
import com.kokuxz.demo.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlatByName(name);
        return null;
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlatByName(name);
    }

    private PlantDTO convertPlantToPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }
}
