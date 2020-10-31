package com.example.promotionengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class signifies a complete order received to checkout module which is collection of one or more order items.
 */
public class Order {

    @JsonProperty("orderItemList")
    private List<OrderItem> orderItemList;

    public Order() {
        super();
    }

    public Order(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
}
