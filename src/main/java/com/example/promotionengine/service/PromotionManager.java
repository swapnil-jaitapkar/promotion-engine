package com.example.promotionengine.service;

import com.example.promotionengine.model.Promotion;

import java.util.List;

/**
 * Interface to define functionality to get specific or all Promotions and add or remove Promotions.
 */
public interface PromotionManager {

    void addPromotion(Promotion promotion);

    void removePromotion(int promotionId);

    List<Promotion> getAllPromotions();
}
