package com.example.promotionengine.model;

/**
 * This class signifies a promotion item stating a particular promotion is applied for which SKU and for how many units of SKU.
 */
public class PromotionItem {

    private char SKUName;
    private int quantity;

    public PromotionItem(char SKUName, int quantity) {
        this.SKUName = SKUName;
        this.quantity = quantity;
    }

    public char getSKUName() {
        return SKUName;
    }

    public int getQuantity() {
        return quantity;
    }
}
