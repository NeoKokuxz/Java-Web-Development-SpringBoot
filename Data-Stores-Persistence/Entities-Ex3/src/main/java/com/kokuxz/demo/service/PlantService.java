package com.kokuxz.demo.service;

import com.kokuxz.demo.entity.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlatByName(String name){
        return new Plant();
    }
}
