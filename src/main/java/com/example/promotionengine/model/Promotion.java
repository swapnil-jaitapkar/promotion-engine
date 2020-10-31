package com.example.promotionengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class signifies a unique promotion which can be applied during checkout to give special prices to customer selecting appropriate SKUs.
 */
public class Promotion {

    @JsonProperty("promotionId")
    private int promotionId;
    @JsonProperty("promotionItemList")
    private List<PromotionItem> promotionItemList = new ArrayList<>();
    /**
     * promotionPrice indicates final promotion price for a bundle after applying all discount.
     */
    @JsonProperty("promotionPrice")
    private double promotionPrice;
    /**
     * promotionDiscount indicates total discount given for a bundle by considering difference between promotion price and original bundle price.
     */
    @JsonProperty("promotionDiscount")
    private float promotionDiscount;

    public Promotion() {
        super();
    }

    /**
     * Overloaded constructor to cover promotions where there is special discount during a promotion for a specific promotion item.
     *
     * @param promotionId       Unique id for this promotion.
     * @param promotionItemList List of promotion items which are included for this promotion.
     * @param promotionDiscount special discount for the promotion.
     */
    public Promotion(int promotionId, List<PromotionItem> promotionItemList, float promotionDiscount) {
        this.promotionId = promotionId;
        this.promotionItemList = promotionItemList;
        this.promotionDiscount = promotionDiscount;
    }

    /**
     * Overloaded constructor to cover promotions where there is no special discount and promotion is based only on the combinations of promotion items and a fixed price for that bundle.
     *
     * @param promotionId       Unique id for this promotion.
     * @param promotionItemList List of promotion items which are included for this promotion.
     * @param promotionPrice    A fixed price for given set of promotion item bundles.
     */
    public Promotion(int promotionId, List<PromotionItem> promotionItemList, double promotionPrice) {
        this.promotionId = promotionId;
        this.promotionItemList = promotionItemList;
        this.promotionPrice = promotionPrice;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public List<PromotionItem> getPromotionItemList() {
        return promotionItemList;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public float getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public void setPromotionDiscount(float promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }
}
