package com.example.promotionengine.controller;

import com.example.promotionengine.model.Promotion;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface to define REST APLs to add remove and list promotions.
 */
public interface PromotionEngineController {

    @RequestMapping(value = "/getAllActivePromotions", method = RequestMethod.GET)
    List<Promotion> getAllActivePromotions();

    @RequestMapping(value = "/addPromotion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addPromotion(@RequestBody Promotion promotion);

    @RequestMapping(value = "/removePromotion", method = RequestMethod.GET)
    void removePromotion(@RequestParam int promotionId);
}
