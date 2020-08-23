package com.kokuxz.entities.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "plant")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flowerId;

    @Nationalized
    private String name;
    private String color;
    @Column(precision = 12, scale = 4)
    private double price;

    public Flower() {
    }

    public Flower(String name, String color, double price, Long flowerId) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.flowerId = flowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
