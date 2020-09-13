package com.kokuxz.demo.service;

import com.kokuxz.demo.entity.Delivery;
import com.kokuxz.demo.projection.RecipientAndPrice;
import com.kokuxz.demo.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryService (DeliveryRepository deliveryRepository){
        this.deliveryRepository = deliveryRepository;
    }

    public Long save(Delivery delivery){
        delivery.getPlantList().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public RecipientAndPrice getBill(Long deliveryId){
        return deliveryRepository.getBill(deliveryId);
    }
}
