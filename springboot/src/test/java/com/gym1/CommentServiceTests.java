package com.gym1;


import com.gym1.entity.Comment;
import com.gym1.entity.ItemOrder;
import com.gym1.service.CommentService;
import com.gym1.service.ItemOrderService;
import com.gym1.service.ItemService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;



@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceTests {

    @Autowired
    private static CommentService commentService;

    @Autowired
    private static ItemOrderService itemOrderService;

    @Test
    public static void makeComment01() throws MessagingException {
        Map<String, Object> map = new HashMap<>();
        map.put("number", "10");
        int a = itemOrderService.makeOrder(10, 1+"", map);
        int res = commentService.makeComment(1, 7, "---------");

    }
}
