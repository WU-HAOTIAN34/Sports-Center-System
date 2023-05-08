package com.gym1;

import com.gym1.entity.Item;
import com.gym1.service.ItemService;
import com.gym1.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

//    @Test
//    public void contextLoads() {
//        boolean res = itemService.addItem("apple", "aaa", 12.3, "asdasdasda");
//        Assert.assertFalse(res);
//    }

}
