package com.example.dogdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {

    @GetMapping("/dog")
    public void getDogData(){

    }
}
