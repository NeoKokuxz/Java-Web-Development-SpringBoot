package com.kokuxz.demo.dao;

import com.kokuxz.demo.data.CandyData;

import java.util.List;

public interface CandyDAO {
    List<CandyData> candyList();
    void addToDelivery(Long candyId, Long deliveryId);
    List<CandyData> findByDelivery(Long deliveryId);
}
