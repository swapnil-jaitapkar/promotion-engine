package com.example.promotionengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class signifies a single order item specifying SKU and number of units of SKU in the order.
 */
public class OrderItem {

    @JsonProperty("skuname")
    private char SKUName;
    @JsonProperty("unit")
    private int unit;

    public OrderItem() {
        super();
    }

    public OrderItem(char SKUName, int unit) {
        this.SKUName = SKUName;
        this.unit = unit;
    }

    public char getSKUName() {
        return SKUName;
    }

    public int getUnit() {
        return unit;
    }

    /**
     * Method to reduce units after promotion is successfully applied.
     *
     * @param unitsToReduce Number of units to reduce.
     */
    public void reduceUnits(int unitsToReduce) {
        this.unit -= unitsToReduce;
    }
}
