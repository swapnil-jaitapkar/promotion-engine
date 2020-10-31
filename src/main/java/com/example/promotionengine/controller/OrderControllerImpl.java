package com.example.promotionengine.controller;

import com.example.promotionengine.model.Order;
import com.example.promotionengine.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of OrderController interface. Provides functional REST APLs to calculate order value.
 */
@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    OrderManager orderManager;

    @Override
    public double calculateFinalOrderValue(Order order) {
        return orderManager.calculateOrderValue(order);
    }
}
