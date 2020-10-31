package com.example.promotionengine.service;

import com.example.promotionengine.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of OrderManager interface. Implemented logic to provide functionality to calculate order value after applying promotions which give maximum discount to customer.
 */
@Service
public class OrderManagerImpl implements OrderManager {

    @Autowired
    SKUManager skuManager;

    @Autowired
    PromotionManager promotionManager;

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
        List<Promotion> allPromotions = promotionManager.getAllPromotions();
        Collections.sort(allPromotions, Comparator.comparing(Promotion::getPromotionDiscount));
        Collections.reverse(allPromotions);//Sorting all promotions in descending order based on possible discount so that promotions are prioritized to give maximum benefit to customer.
        for (Promotion promotion : allPromotions) {
            while (isPromotionApplicable(promotion, orderItemList)) {
                orderValue += applyPromotion(promotion, orderItemList);
            }
        }
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

    /**
     * Method to check if given promotion is applicable for given list of order items. Returns true if current order item names and quantity satisfy the requirement for promotion to be applicable.
     *
     * @param promotion     Promotion to verify if its applicable.
     * @param orderItemList list of order items to validate promotion against.
     * @return boolean value indicating if the given promotion is applicable for given list of order items.
     */
    private boolean isPromotionApplicable(Promotion promotion, List<OrderItem> orderItemList) {
        List<PromotionItem> promotionItemList = promotion.getPromotionItemList();
        int promotionMatchCounter = 0;
        for (PromotionItem promotionItem : promotionItemList) {
            for (OrderItem orderItem : orderItemList) {
                if ((orderItem.getSKUName() == promotionItem.getSKUName() && orderItem.getUnit() >= promotionItem.getQuantity()))
                    promotionMatchCounter++;
            }
        }
        if (promotionMatchCounter == promotionItemList.size())
            return true;
        else
            return false;
    }

    /**
     * Method that applies given promotion to the order and returns the promotion value in response.
     * This will also reduce the quantity of order item when a promotion is applied.
     * Note that its must to check if given promotion is applicable before applying the promotion.
     *
     * @param promotion     Promotion to be applied.
     * @param orderItemList List of order items on which promotion should be applied.
     * @return discounted promotion value after promotion is applied.
     */
    private double applyPromotion(Promotion promotion, List<OrderItem> orderItemList) {
        List<PromotionItem> promotionItemList = promotion.getPromotionItemList();
        for (PromotionItem promotionItem : promotionItemList) {
            orderItemList.stream().filter(orderItem -> orderItem.getSKUName() == promotionItem.getSKUName()).collect(Collectors.toList()).get(0).reduceUnits(promotionItem.getQuantity());
        }
        return promotion.getPromotionPrice();
    }
}
