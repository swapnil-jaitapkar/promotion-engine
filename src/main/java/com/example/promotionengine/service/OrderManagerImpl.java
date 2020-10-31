package com.example.promotionengine.service;

import com.example.promotionengine.model.Order;
import com.example.promotionengine.model.OrderItem;
import com.example.promotionengine.model.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of OrderManager interface. Implemented logic to provide functionality to calculate order value after applying promotions which give maximum discount to customer.
 */
@Service
public class OrderManagerImpl implements OrderManager {

    @Autowired
    SKUManager skuManager;

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
     * Method to provide order value without any promotions applied.
     *
     * @param orderItemList List of order items present in given order
     * @return order value calculated by considering original values of the SKUs and order quantity.
     */
    private double calculateOrderWithoutPromotions(List<OrderItem> orderItemList) {
        double orderValue = 0;
        for (OrderItem orderItem : orderItemList) {
            SKU sku = skuManager.getSKU(orderItem.getSKUName());
            if (sku == null) {
                throw new IllegalArgumentException("One of the SKU " + orderItem.getSKUName() + " is not present");
            }
            orderValue += orderItem.getUnit() * sku.getPrice();
        }
        return orderValue;
    }
}
