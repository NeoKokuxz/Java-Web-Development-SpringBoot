package com.kokuxz.entities.entity;

import com.kokuxz.entities.pk.PersonPK;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Delivery {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @EmbeddedId
    PersonPK id;

    @Nationalized
    private String name;

    @Column(name = "address_full", length = 500)
    private String address;
//    private LocalDate deliveryDate;
//    private LocalTime deliveryTime;

    private LocalDateTime localDateTime;
    @Type(type = "yes_no")
    private char completed;

    public Delivery() {
    }

    public char getCompleted() {
        return completed;
    }

    public void setCompleted(char completed) {
        this.completed = completed;
    }

    public Delivery(String name, String address, LocalDateTime time, char completed) {
        this.address = address;
        this.name = name;
        this.localDateTime = time;
        this.completed = completed;
    }

    public PersonPK getId() {
        return id;
    }

    public void setId(PersonPK id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
