package com.kokuxz.jpa.service;

import com.kokuxz.jpa.entity.Delivery;
import com.kokuxz.jpa.projection.RecipientAndPrice;
import com.kokuxz.jpa.repository.DeliveryRepository;
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
