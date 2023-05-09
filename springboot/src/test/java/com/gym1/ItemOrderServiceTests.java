package com.gym1;
import com.gym1.entity.ItemOrder;
import com.gym1.service.ItemOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemOrderServiceTests {

    @Autowired
    private ItemOrderService itemOrderService;

    @Test
    public void makeOrderTest01() throws MessagingException {
        Map<String, Object> map = new HashMap<>();
        map.put("number", "12");
        map.put("status", "null");
        int res = itemOrderService.makeOrder(10, 1+"", map);
        Assert.assertEquals(1, 1);
    }

    @Test
    public void getUserAllOrderTest01(){
        List<ItemOrder> itemOrders = itemOrderService.getUserAllOrder(1);
        ItemOrder itemOrder = itemOrders.get(itemOrders.size()-1);
        Assert.assertEquals(0, itemOrder.getUserId());
    }

    @Test
    public void getUserAllOrderTest02(){
        List<ItemOrder> itemOrders = itemOrderService.getUserAllOrder(1);
        ItemOrder itemOrder = itemOrders.get(itemOrders.size()-1);
        Assert.assertEquals("Zhang San", itemOrder.getName());
    }



}
