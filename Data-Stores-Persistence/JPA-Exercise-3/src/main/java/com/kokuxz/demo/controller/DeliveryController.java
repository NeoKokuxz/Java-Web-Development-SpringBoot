package com.kokuxz.demo.controller;

import com.kokuxz.demo.entity.Delivery;
import com.kokuxz.demo.projection.RecipientAndPrice;
import com.kokuxz.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery (@RequestBody Delivery delivery){
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId){
        return deliveryService.getBill(deliveryId);
    }

}
