package com.example.promotionengine.controller;

import com.example.promotionengine.model.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Interface to define REST APL to calculateFinalOrder.
 */
public interface OrderController {

    @RequestMapping(value = "/calculateFinalOrderValue", method = RequestMethod.POST)
    double calculateFinalOrderValue(@RequestBody Order order);
}
