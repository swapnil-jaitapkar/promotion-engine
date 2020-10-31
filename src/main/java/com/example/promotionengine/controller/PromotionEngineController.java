package com.example.promotionengine.controller;

import com.example.promotionengine.model.Promotion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Interface to define REST APLs to add remove and list promotions.
 */
public interface PromotionEngineController {

    @RequestMapping(value = "/getAllActivePromotions", method = RequestMethod.GET)
    List<Promotion> getAllActivePromotions();

}
