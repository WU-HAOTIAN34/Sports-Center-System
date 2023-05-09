package com.gym1;
import com.gym1.entity.Item;
import com.gym1.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTests {

    @Autowired
    private ItemService itemService;

    @Test
    public void addItemTest01(){
        int res = itemService.addItem("apple",  "asd", 30.9,"asd");
        Assert.assertEquals(1, res);
    }

    @Test
    public void addItemTest02(){
        int res = itemService.addItem("apple",  "asd", 30.9,"asd");
        Assert.assertEquals(-2, res);
    }



}
