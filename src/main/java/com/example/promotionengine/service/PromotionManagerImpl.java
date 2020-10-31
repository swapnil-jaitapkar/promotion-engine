package com.example.promotionengine.service;

import com.example.promotionengine.model.Promotion;
import com.example.promotionengine.model.PromotionItem;
import com.example.promotionengine.model.SKU;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PromotionManager interface. Implemented logic to provide functionality to get specific or all Promotions and add or remove Promotions.
 */
public class PromotionManagerImpl implements PromotionManager {

    private List<Promotion> promotionList = new ArrayList<>();

    private SKUManager skuManager;

    /**
     * Constructor to add some default promotions while initializing as per requirement.
     * This constructor makes sure that while adding the new promotion all the necessary values for promotion discount and final promotion price are calculated and assigned.
     *
     * @param skuManager Dependency injection for SKUManager instance.
     */
    @Autowired
    public PromotionManagerImpl(SKUManager skuManager) {
        this.skuManager = skuManager;
        List<PromotionItem> promotionItemList1 = new ArrayList<>();
        promotionItemList1.add(new PromotionItem('A', 3));
        Promotion promotion1 = new Promotion(1, promotionItemList1, 130d);
        List<PromotionItem> promotionItemList2 = new ArrayList<>();
        promotionItemList2.add(new PromotionItem('B', 2));
        Promotion promotion2 = new Promotion(2, promotionItemList2, 45d);
        List<PromotionItem> promotionItemList3 = new ArrayList<>();
        promotionItemList3.add(new PromotionItem('C', 1));
        promotionItemList3.add(new PromotionItem('D', 1));
        Promotion promotion3 = new Promotion(3, promotionItemList3, 30d);
        this.addPromotion(promotion1);
        this.addPromotion(promotion2);
        this.addPromotion(promotion3);
    }

    /**
     * Method to add new promotion.
     * This method takes care to calculate and assign total promotion discount if promotion is of type where final promotion price is given.
     * This method takes care to calculate and assign final promotion price if promotion is of type where promotion discount is given.
     *
     * @param promotion New promotion to be added
     */
    @Override
    public void addPromotion(Promotion promotion) {
        if (promotion.getPromotionDiscount() > 0 || promotion.getPromotionPrice() > 0) {
            if (promotion.getPromotionDiscount() == 0) {
                promotion.setPromotionDiscount(calculatePromotionDiscount(promotion.getPromotionItemList(), promotion.getPromotionPrice()));
            } else {
                promotion.setPromotionPrice(calculatePromotionPrice(promotion.getPromotionItemList(), promotion.getPromotionDiscount()));
            }
        }
        promotionList.add(promotion);
        //TODO:- Invalid promotion as both disount and promotion price are not present so it wont be added. Further enhancements exception Can be thrown.
    }

    /**
     * Method to remove a specific promotion based on promotion id.
     *
     * @param promotionId Id of promotion to be removed.
     */
    @Override
    public void removePromotion(int promotionId) {
        promotionList.removeIf(promotion -> promotion.getPromotionId() == promotionId);
    }

    /**
     * This method returns all the available promotions.
     *
     * @return All available promotions.
     */
    @Override
    public List<Promotion> getAllPromotions() {
        return promotionList;
    }

    /**
     * This methods help calculating total actual price of a bundle.
     *
     * @param promotionItemList List of promotion items which are included for this promotion.
     * @return original price of the bundle without considering any promotion.
     */
    private double calculateActualTotalPrice(List<PromotionItem> promotionItemList) {
        double totalPrice = 0;
        for (PromotionItem promotionItem : promotionItemList) {
            SKU sku = skuManager.getSKU(promotionItem.getSKUName());
            if (sku != null) {
                totalPrice += sku.getPrice() * promotionItem.getQuantity();
            } else {
                throw new IllegalArgumentException("One of the SKU " + promotionItem.getSKUName() + " is not present");
            }
        }
        return totalPrice;
    }


    /**
     * This method helps in calculating total promotion discount for a bundle
     *
     * @param promotionItemList List of promotion items which are included for this promotion.
     * @param promotionPrice    A fixed price for given set of promotion item bundles.
     * @return promotion discount after considering original price of promotion and final promotion price.
     */
    private float calculatePromotionDiscount(List<PromotionItem> promotionItemList, double promotionPrice) {
        float promotionDiscount = 0;
        double totalPrice = calculateActualTotalPrice(promotionItemList);
        double savings = totalPrice - promotionPrice;
        if (savings > 0) {
            BigDecimal bigDecimal = new BigDecimal(Float.toString((float) (savings / totalPrice) * 100));
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            promotionDiscount = bigDecimal.floatValue();
        }
        return promotionDiscount;
    }


    /**
     * This method helps in calculating total promotion price for a bundle.
     *
     * @param promotionItemList List of promotion items which are included for this promotion.
     * @param promotionDiscount special discount for the promotion.
     * @return Final promotion price of the bundle considering orignal price of bundle and discount applied.
     */
    private double calculatePromotionPrice(List<PromotionItem> promotionItemList, float promotionDiscount) {
        double promotionPrice;
        promotionPrice = calculateActualTotalPrice(promotionItemList) * (100 - promotionDiscount) / 100;
        return promotionPrice;
    }
}
