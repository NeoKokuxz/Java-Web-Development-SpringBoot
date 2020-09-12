package com.kokuxz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.kokuxz.demo.jsonview.Views;
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

    //Only the JsonView data will be return to front end
    @JsonView(Views.Public.class)
    @Nationalized //Use Nationalized instead of @Type=nstring
    private String name;
    @JsonView(Views.Public.class)
    @Column(precision = 12, scale = 4)
    private BigDecimal price; //BigDecimal is Java Standard class for currency

    @ManyToOne //many plants can belong to one delivery
    @JoinColumn(name = "delivery_id") //map the join column in the plant table
    private Delivery delivery;

    /* getters and setters*/
}
