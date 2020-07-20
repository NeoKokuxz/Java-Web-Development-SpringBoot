package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simplehome")
public class SimpleHomeController {

    @GetMapping()
    public String firstVisit(Model model) {
        model.addAttribute("firstVisit", true);
        return "simple-home";
    }

    @PostMapping()
    public String subsequentVisit(Model model) {
        model.addAttribute("firstVisit", false);
        return "simple-home";
    }
}