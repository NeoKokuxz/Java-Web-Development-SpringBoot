package com.kokuxz.exercise.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

// Uses InheritanceType.JOINED to store shared fields in 'plant' and unique fields
// in subclass tables
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized //Use Nationalized instead of @Type=nstring
    private String name;
    @Column(precision = 12, scale = 4)
    private BigDecimal price; //BigDecimal is Java Standard class for currency

    @ManyToOne //many plants can belong to one delivery
    @JoinColumn(name = "delivery_id") //map the join column in the plant table
    private Delivery delivery;

    /* getters and setters*/
}
