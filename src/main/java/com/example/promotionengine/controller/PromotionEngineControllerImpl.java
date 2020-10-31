package com.example.promotionengine.controller;

import com.example.promotionengine.model.Promotion;
import com.example.promotionengine.service.PromotionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of PromotionEngineController interface. Provides functional REST APLs to add remove and list promotions.
 */
@RestController
public class PromotionEngineControllerImpl implements PromotionEngineController {

    @Autowired
    private PromotionManager promotionManager;

    /**
     * REST API to list all active promotions.
     *
     * @return list of all active promotions.
     */
    @Override
    public List<Promotion> getAllActivePromotions() {
        return promotionManager.getAllPromotions();
    }
}
