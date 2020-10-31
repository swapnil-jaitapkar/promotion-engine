package com.example.promotionengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class signifies a promotion item stating a particular promotion is applied for which SKU and for how many units of SKU.
 */
public class PromotionItem {

    @JsonProperty("skuname")
    private char SKUName;
    @JsonProperty("quantity")
    private int quantity;

    public PromotionItem() {
        super();
    }

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
