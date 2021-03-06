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


    /**
     * Test case to verify if some default SKUs are added during initialization and verify if getAllSKU method is working correctly
     */
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

    /**
     * Test case to verify functionality to verify addition of new SKU and to check if the changes are persisted.
     */
    @Test
    public void addSKUTest() {
        skuManager.addSKU(new SKU('E', 60));
        List<SKU> actualSKUList = skuManager.getAllSKU();
        List<SKU> expectedSKUList = new ArrayList<>();
        expectedSKUList.add(new SKU('A', 50));
        expectedSKUList.add(new SKU('B', 30));
        expectedSKUList.add(new SKU('C', 20));
        expectedSKUList.add(new SKU('D', 15));
        expectedSKUList.add(new SKU('E', 60));
        compareSKULists(expectedSKUList, actualSKUList);
    }

    /**
     * Test case to verify functionality to remove SKU from the list.
     */
    @Test
    public void removeSKUTest() {
        skuManager.removeSKU('C');
        List<SKU> actualSKUList = skuManager.getAllSKU();
        List<SKU> expectedSKUList = new ArrayList<>();
        expectedSKUList.add(new SKU('A', 50));
        expectedSKUList.add(new SKU('B', 30));
        expectedSKUList.add(new SKU('D', 15));
        compareSKULists(expectedSKUList, actualSKUList);
    }

    /**
     * Test case to verify functionality to add and remove SKU is working together.
     */
    @Test
    public void addAndRemoveSKUTest() {
        skuManager.addSKU(new SKU('E', 60));
        skuManager.removeSKU('C');
        List<SKU> actualSKUList = skuManager.getAllSKU();
        List<SKU> expectedSKUList = new ArrayList<>();
        expectedSKUList.add(new SKU('A', 50));
        expectedSKUList.add(new SKU('B', 30));
        expectedSKUList.add(new SKU('D', 15));
        expectedSKUList.add(new SKU('E', 60));
        compareSKULists(expectedSKUList, actualSKUList);
    }

    /**
     * Test case to verify functionality to get specific SKU based on SKU name.
     */
    @Test
    public void getSKUTest() {
        SKU a = skuManager.getSKU('A');
        Assert.assertNotNull(a);
        Assert.assertEquals('A', a.getName());
        Assert.assertEquals(50, a.getPrice(), 0);
    }

    /**
     * Test case to verify functionality to get specific SKU based on SKU name which is not present in SKU list.
     */
    @Test
    public void getUnavailableSKUTest() {
        SKU e = skuManager.getSKU('E');
        Assert.assertNull(e);
    }


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