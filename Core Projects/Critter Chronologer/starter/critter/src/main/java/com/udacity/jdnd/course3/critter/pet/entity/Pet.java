package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.customer.entity.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
//@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany
    private List<Schedule> scheduleList;

    private LocalDate birthDate;

    private String notes;


}
