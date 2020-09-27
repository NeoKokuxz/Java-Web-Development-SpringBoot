package com.kokuxz.jpa;

import com.kokuxz.jpa.entity.Delivery;
import com.kokuxz.jpa.entity.Plant;
import com.kokuxz.jpa.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TestExercise4 {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPriceLessThan(){
        Plant p = testEntityManager.persist(new Plant("Test Plant 1", 4.99));
        testEntityManager.persist(new Plant ("Test Plant 2", 5.99));

        List<Plant> cheapPlant = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));

        Assertions.assertEquals(1, cheapPlant.size(), "Size");
        Assertions.assertEquals(p.getId(), cheapPlant.get(0).getId(), "Id");

        System.out.println(p.getName());
        System.out.println(cheapPlant.get(0).getName());
    }

    @Test
    public void testDeliveryCompletion (){
        Plant p = testEntityManager.persist(new Plant("Plant Test 1", 4.99));
        Delivery d = testEntityManager.persist(new Delivery("Delivery Test 1", "Test Address", LocalDateTime.now()));

        d.setPlantList(Lists.newArrayList(p));
        p.setDelivery(d);

        //Test both before and after
        Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }

}
