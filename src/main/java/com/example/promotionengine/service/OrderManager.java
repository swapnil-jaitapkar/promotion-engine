package com.example.promotionengine.service;

import com.example.promotionengine.model.Order;

/**
 * Interface to define functionality to calculate order value after applying promotions which give maximum discount to customer.
 */
public interface OrderManager {

    double calculateOrderValue(Order order);
}
