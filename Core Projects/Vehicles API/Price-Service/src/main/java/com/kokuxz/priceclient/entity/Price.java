package com.kokuxz.priceclient.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import lombok.*;

/**
 * Represents the price of a given vehicle, including currency.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Price {
    @Id
    //@Column(name ="VEHICLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String currency;
    private BigDecimal price;
}
