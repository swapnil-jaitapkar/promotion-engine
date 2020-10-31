package com.example.promotionengine.service;

import com.example.promotionengine.model.Promotion;
import com.example.promotionengine.model.PromotionItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionManagerImplTest {

    private PromotionManager promotionMannager;

    @Before
    public void before() {
        promotionMannager = new PromotionManagerImpl(new SKUManagerImpl());
    }

    @After
    public void after() {
        promotionMannager = null;
    }


    /**
     * Test case to verify if getAllPromotions functionality is working.
     * Also to check if by default few promotions are added.
     * Also to verify if all necessary values for promotions like promotion discount and final promotion price are automatically calculated and assigned.
     */
    @Test
    public void getAllPromotionsTest() {
        List<Promotion> actualPromotionList = promotionMannager.getAllPromotions();
        List<Promotion> expectedPromotionList = new ArrayList<>();
        List<PromotionItem> promotionItemList1 = new ArrayList<>();
        promotionItemList1.add(new PromotionItem('A', 3));
        Promotion promotion1 = new Promotion(1, promotionItemList1, 130d);
        promotion1.setPromotionDiscount(13.33f);
        List<PromotionItem> promotionItemList2 = new ArrayList<>();
        promotionItemList2.add(new PromotionItem('B', 2));
        Promotion promotion2 = new Promotion(2, promotionItemList2, 45d);
        promotion2.setPromotionDiscount(25f);
        List<PromotionItem> promotionItemList3 = new ArrayList<>();
        promotionItemList3.add(new PromotionItem('C', 1));
        promotionItemList3.add(new PromotionItem('D', 1));
        Promotion promotion3 = new Promotion(3, promotionItemList3, 30d);
        promotion3.setPromotionDiscount(14.29f);
        expectedPromotionList.add(promotion1);
        expectedPromotionList.add(promotion2);
        expectedPromotionList.add(promotion3);
        comparePromotionLists(expectedPromotionList, actualPromotionList);

    }

    private void comparePromotionLists(List<Promotion> expectedPromotionList, List<Promotion> actualPromotionList) {
        Assert.assertEquals(expectedPromotionList.size(), actualPromotionList.size());
        for (int i = 0; i < expectedPromotionList.size(); i++) {
            Promotion actualPromotion = actualPromotionList.get(i);
            Promotion expectedPromotion = expectedPromotionList.get(i);
            Assert.assertEquals(expectedPromotion.getPromotionId(), actualPromotion.getPromotionId());
            Assert.assertEquals(expectedPromotion.getPromotionDiscount(), actualPromotion.getPromotionDiscount(), 0);
            Assert.assertEquals(expectedPromotion.getPromotionPrice(), actualPromotion.getPromotionPrice(), 0);
            List<PromotionItem> expectedPromotionItemList = expectedPromotion.getPromotionItemList();
            List<PromotionItem> actualPromotionItemList = actualPromotion.getPromotionItemList();
            Assert.assertEquals(expectedPromotionItemList.size(), actualPromotionItemList.size());
            for (int j = 0; j < expectedPromotionItemList.size(); j++) {
                PromotionItem expectedPromotionItem = expectedPromotionItemList.get(j);
                PromotionItem actualPromotionItem = actualPromotionItemList.get(j);
                Assert.assertEquals(expectedPromotionItem.getSKUName(), actualPromotionItem.getSKUName());
                Assert.assertEquals(expectedPromotionItem.getQuantity(), actualPromotionItem.getQuantity());
            }

        }
    }

    /**
     * Test case to verify functionality of adding new promotion with final price is working and also to verify if all necessary values for promotions like promotion discount and final promotion price are automatically calculated and assigned.
     */
    @Test
    public void addPromotionWithPriceTest() {
        List<PromotionItem> newPromotionItemList = new ArrayList<>();
        newPromotionItemList.add(new PromotionItem('C', 2));
        Promotion newPromotion = new Promotion(4, newPromotionItemList, 35d);
        promotionMannager.addPromotion(newPromotion);
        List<Promotion> actualPromotionList = promotionMannager.getAllPromotions();
        List<Promotion> expectedPromotionList = new ArrayList<>();
        List<PromotionItem> promotionItemList1 = new ArrayList<>();
        promotionItemList1.add(new PromotionItem('A', 3));
        Promotion promotion1 = new Promotion(1, promotionItemList1, 130d);
        promotion1.setPromotionDiscount(13.33f);
        List<PromotionItem> promotionItemList2 = new ArrayList<>();
        promotionItemList2.add(new PromotionItem('B', 2));
        Promotion promotion2 = new Promotion(2, promotionItemList2, 45d);
        promotion2.setPromotionDiscount(25f);
        List<PromotionItem> promotionItemList3 = new ArrayList<>();
        promotionItemList3.add(new PromotionItem('C', 1));
        promotionItemList3.add(new PromotionItem('D', 1));
        Promotion promotion3 = new Promotion(3, promotionItemList3, 30d);
        promotion3.setPromotionDiscount(14.29f);
        newPromotion.setPromotionDiscount(12.5f);
        expectedPromotionList.add(promotion1);
        expectedPromotionList.add(promotion2);
        expectedPromotionList.add(promotion3);
        expectedPromotionList.add(newPromotion);
        comparePromotionLists(expectedPromotionList, actualPromotionList);
    }

    /**
     * Test case to verify functionality of adding new promotion with discount is working and also to verify if all necessary values for promotions like promotion discount and final promotion price are automatically calculated and assigned.
     */
    @Test
    public void addPromotionWithDiscountTest() {
        List<PromotionItem> newPromotionItemList = new ArrayList<>();
        newPromotionItemList.add(new PromotionItem('A', 1));
        Promotion newPromotion = new Promotion(4, newPromotionItemList, 40.00f);
        promotionMannager.addPromotion(newPromotion);
        List<Promotion> actualPromotionList = promotionMannager.getAllPromotions();
        List<Promotion> expectedPromotionList = new ArrayList<>();
        List<PromotionItem> promotionItemList1 = new ArrayList<>();
        promotionItemList1.add(new PromotionItem('A', 3));
        Promotion promotion1 = new Promotion(1, promotionItemList1, 130d);
        promotion1.setPromotionDiscount(13.33f);
        List<PromotionItem> promotionItemList2 = new ArrayList<>();
        promotionItemList2.add(new PromotionItem('B', 2));
        Promotion promotion2 = new Promotion(2, promotionItemList2, 45d);
        promotion2.setPromotionDiscount(25f);
        List<PromotionItem> promotionItemList3 = new ArrayList<>();
        promotionItemList3.add(new PromotionItem('C', 1));
        promotionItemList3.add(new PromotionItem('D', 1));
        Promotion promotion3 = new Promotion(3, promotionItemList3, 30d);
        promotion3.setPromotionDiscount(14.29f);
        newPromotion.setPromotionPrice(30);
        expectedPromotionList.add(promotion1);
        expectedPromotionList.add(promotion2);
        expectedPromotionList.add(promotion3);
        expectedPromotionList.add(newPromotion);
        comparePromotionLists(expectedPromotionList, actualPromotionList);
    }

    /*@Test
    void removePromotion() {
    }*/
}