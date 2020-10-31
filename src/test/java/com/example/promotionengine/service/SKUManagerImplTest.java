package com.example.promotionengine.service;

import com.example.promotionengine.model.SKU;
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
public class SKUManagerImplTest {

    private SKUManager skuManager;

    @Before
    public void before() {
        skuManager = new SKUManagerImpl();
    }

    @After
    public void after() {
        skuManager = null;
    }

    @Test
    public void getAllSKUTest() {
        List<SKU> actualSKUList = skuManager.getAllSKU();
        List<SKU> expectedSKUList = new ArrayList<>();
        expectedSKUList.add(new SKU('A', 50));
        expectedSKUList.add(new SKU('B', 30));
        expectedSKUList.add(new SKU('C', 20));
        expectedSKUList.add(new SKU('D', 15));
        compareSKULists(expectedSKUList, actualSKUList);
    }

    /*@Test
    void removeSKU() {
    }

    @Test
    void getAllSKU() {
    }

    @Test
    void getSKU() {
    }*/

    private void compareSKULists(List<SKU> expectedSKUList, List<SKU> actualSKUList) {
        Assert.assertEquals(expectedSKUList.size(), actualSKUList.size());
        for (int i = 0; i < expectedSKUList.size(); i++) {
            SKU expectedSKU = expectedSKUList.get(i);
            SKU actualSKU = actualSKUList.get(i);
            Assert.assertEquals(expectedSKU.getName(), actualSKU.getName());
            Assert.assertEquals(expectedSKU.getPrice(), actualSKU.getPrice(), 0);
        }
    }
}