package com.example.promotionengine.model;

/**
 * This class signifies a stock keeping unit(SKU) with its basic price.
 */
public class SKU {

    private char name;
    private double price;

    public SKU(char name, double price) {
        this.name = name;
        this.price = price;
    }

    public char getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
