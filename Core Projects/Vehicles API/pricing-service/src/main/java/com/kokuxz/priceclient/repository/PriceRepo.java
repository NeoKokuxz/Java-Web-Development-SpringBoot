package com.kokuxz.priceclient.repository;

import com.kokuxz.priceclient.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price, Long> {
}
