package com.example.promotionengine;

import com.example.promotionengine.model.Promotion;
import com.example.promotionengine.model.PromotionItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PromotionEngineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests REST API for getting all active promotions. This should give default promotions added during initialization.
     *
     * @throws Exception
     */
    @Test
    void getAllActivePromotionsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllActivePromotions")).andExpect(status().isOk()).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Promotion.class);
        List<Promotion> actualPromotionList = mapper.readValue(mvcResult.getResponse().getContentAsString(), javaType);
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
        int a = 1;
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


}
