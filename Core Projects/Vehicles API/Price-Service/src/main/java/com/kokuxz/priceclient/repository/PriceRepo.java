package com.kokuxz.priceclient.repository;

import com.kokuxz.priceclient.entity.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepo extends CrudRepository<Price, Long> {
}
