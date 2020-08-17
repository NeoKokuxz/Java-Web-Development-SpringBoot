package com.kokuxz.priceclient.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import lombok.*;

/**
 * Represents the price of a given vehicle, including currency.
 */

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String currency;
    private BigDecimal price;

    public Price() {
    }

    public Price(Long id, String currency, BigDecimal price) {
        this.currency = currency;
        this.id = id;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
