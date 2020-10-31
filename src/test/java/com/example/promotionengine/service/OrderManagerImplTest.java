package com.example.promotionengine.service;

import com.example.promotionengine.model.Order;
import com.example.promotionengine.model.OrderItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderManagerImplTest {

    @Autowired
    OrderManager orderManager;

    /**
     * Test case to calculate order value where no promotions are applicable.
     */
    @Test
    public void calculateOrderValueWithoutPromotionsTest() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 1));
        orderItemList.add(new OrderItem('B', 1));
        orderItemList.add(new OrderItem('C', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 100, 0);
    }

    /**
     * Test case to calculate order value where no promotions are applicable and one of SKU is not present.
     */
    @Test(expected = IllegalArgumentException.class)
    public void calculateOrderValueWithoutPromotionsWhenSKUIsNotPresentTest() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 1));
        orderItemList.add(new OrderItem('B', 1));
        orderItemList.add(new OrderItem('E', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
    }

    /**
     * Test case to calculate order value where no promotions are applicable.
     */
    @Test
    public void calculateOrderValueWithoutPromotionsTest2() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('C', 10));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 200, 0);
    }

    /**
     * Test case to calculate order value where promotions are applicable.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 3));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 130, 0);
    }

    /**
     * Test case to calculate order value where multiple promotions are applicable.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest2() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 3));
        orderItemList.add(new OrderItem('B', 2));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 175, 0);
    }

    /**
     * Test case to calculate order value where multiple promotions are applicable and few items remain which dont have any promotions left.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest3() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 3));
        orderItemList.add(new OrderItem('B', 2));
        orderItemList.add(new OrderItem('C', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 195, 0);
    }

    /**
     * Test case to calculate order value where multiple promotions are applicable and few items remain which dont have any promotions left.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest4() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 4));
        orderItemList.add(new OrderItem('B', 3));
        orderItemList.add(new OrderItem('C', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 275, 0);
    }

    /**
     * Test case to calculate order value where all promotions are applicable.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest5() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 3));
        orderItemList.add(new OrderItem('B', 2));
        orderItemList.add(new OrderItem('C', 1));
        orderItemList.add(new OrderItem('D', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 205, 0);
    }

    /**
     * Test case to calculate order value where all promotions are applicable and few items remain which dont have any promotions left.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest6() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 4));
        orderItemList.add(new OrderItem('B', 3));
        orderItemList.add(new OrderItem('C', 2));
        orderItemList.add(new OrderItem('D', 1));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 305, 0);
    }

    /**
     * Test case to calculate order value where same promotion is applied multiple times.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest7() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 6));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 260, 0);
    }

    /**
     * Test case to calculate order value where multiple promotions are applied multiple times.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest8() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 6));
        orderItemList.add(new OrderItem('B', 6));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 395, 0);
    }

    /**
     * Test case to calculate order value where all promotions are applied multiple times.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest9() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 6));
        orderItemList.add(new OrderItem('B', 6));
        orderItemList.add(new OrderItem('C', 2));
        orderItemList.add(new OrderItem('D', 2));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 455, 0);
    }

    /**
     * Test case to calculate order value where all promotions are applied multiple times and still few items are left for which promotions are not applicable.
     */
    @Test
    public void calculateOrderValueWithPromotionsTest10() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem('A', 7));
        orderItemList.add(new OrderItem('B', 6));
        orderItemList.add(new OrderItem('C', 2));
        orderItemList.add(new OrderItem('D', 2));
        Order order = new Order(orderItemList);
        double v = orderManager.calculateOrderValue(order);
        Assert.assertEquals(v, 505, 0);
    }
}