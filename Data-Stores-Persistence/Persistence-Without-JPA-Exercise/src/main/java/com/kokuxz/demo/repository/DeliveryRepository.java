package com.kokuxz.demo.repository;

import com.kokuxz.demo.entity.Delivery;
import com.kokuxz.demo.entity.Plant;
import com.kokuxz.demo.projection.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }

    public Delivery find (Long id){
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }

    public void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    /* JPA Exercise 2 */
    public List<Delivery> findDeliveryByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    // One possible way to solve this - query a list of Plants with deliveryId matching
    // the one provided and sum their prices.
    public RecipientAndPrice getBill(Long deliveryId){
        //Create Critieria by using CriteriaBuilder from entityManager
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Create Query using RecipientAndPrice to project limited data to presentation layer from the projection class
        //Save it to CriteriaQuery
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        //Set root to the Plant.class to hold data
        Root<Plant> root = query.from(Plant.class);
        //Below code is simply:
        // select name, price from Plant where Plant.get delivery id = id
        query.select(cb.construct(
                RecipientAndPrice.class, root.get("delivery").get("name"),
                cb.sum(root.get("price")))
        ).where(cb.equal(root.get("delivery").get("id"), deliveryId));
        //Return the single result from the query above
        return entityManager.createQuery(query).getSingleResult();
    }
}