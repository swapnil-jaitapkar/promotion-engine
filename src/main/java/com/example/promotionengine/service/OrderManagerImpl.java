package com.example.promotionengine.service;

import com.example.promotionengine.model.Order;
import com.example.promotionengine.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of OrderManager interface. Implemented logic to provide functionality to calculate order value after applying promotions which give maximum discount to customer.
 */
@Service
public class OrderManagerImpl implements OrderManager {

    /**
     * Method to calculate final order value considering best possible promotions applicable towards the items in the order to give maximum benefit to customer.
     *
     * @param order Order received for calculating final price.
     * @return Final price for given order after applying best possible promotions.
     */
    @Override
    public double calculateOrderValue(Order order) {
        double orderValue = 0;
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderValue += calculateOrderWithoutPromotions(orderItemList);
        return orderValue;
    }

    /**
     * Dummmy method to provide value without any promotions applied.
     *
     * @param orderItemList List of order items present in given order
     * @return Dummy value.
     */
    private double calculateOrderWithoutPromotions(List<OrderItem> orderItemList) {
        double orderValue = 100;
        return orderValue;
    }
}
