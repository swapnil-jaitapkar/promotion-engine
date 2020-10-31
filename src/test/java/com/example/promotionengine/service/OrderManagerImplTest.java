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
}