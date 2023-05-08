package com.gym1.service;


import com.gym1.entity.Item;
import com.gym1.entity.ItemOrder;
import com.gym1.entity.User;
import com.gym1.mapper.ItemMapper;
import com.gym1.mapper.ItemOrderMapper;
import com.gym1.mapper.UserMapper;
import com.gym1.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ItemOrderService {

    @Autowired
    ItemOrderMapper itemOrderMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    UserMapper userMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public int makeOrder(int itemId, String userId, Map map) throws MessagingException {
        int uId = Integer.parseInt(userId);
        Item item = itemMapper.queryItemById(itemId).get(0);
        User user = userMapper.queryUserById(uId);
        double itemPrice = item.getPrice();
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();
        String username = user.getUsername();
        int number = Integer.parseInt(map.get("number").toString());
        Date date = new Date(System.currentTimeMillis());
        double price = itemPrice * number;
        String status = map.get("status").toString();
        if (status.equals("user")){
            User user1 = userMapper.queryUserById(uId);
            if (user1.getIsMember() == 1){
                int t = (int)(price * 0.75 * 100);
                price = (double)(t / 100);
            }
        }
        ItemOrder itemOrder = new ItemOrder(uId, itemId, number, price,
                username, name, phoneNumber, date);
        int res = 0;
        try{
            res = itemOrderMapper.addItemOrder(itemOrder);
            if (res != 0){
                MimeMessage message = mailSender.createMimeMessage();
                Item item2 = itemMapper.queryItemById(itemId).get(0);
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
                mimeMessageHelper.setFrom(from);
                User user1 = userMapper.queryUserById(uId);
                mimeMessageHelper.setTo(user.getEmail());
                mimeMessageHelper.setSubject("Order Successfully");
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
                String year = formatter.format(date).substring(0,4);
                String month = formatter.format(date).substring(5,7);
                String day = formatter.format(date).substring(8,10);
                String content = EmailUtil.itemOrderEmail(year, month, day, user1.getName(),
                        user1.getPhoneNumber(), user1.getUsername(), item2.getItemName(),
                        number, price+"", formatter.format(date));
                mimeMessageHelper.setText(content, true);
                mailSender.send(message);
            }
            return res;
        }catch (Exception e){
            res = -1;
        }
        return res;
    }

    public List<ItemOrder> getUserAllOrder(int id){
        return itemOrderMapper.queryItemOrderByUserId(id);
    }

    public List<ItemOrder> getUserUncommentOrder(int id){
        return itemOrderMapper.queryItemOrderByUserIdComment(id);
    }

    public int deleteOrder(int id){
        int res = 0;
        try{
            res = itemOrderMapper.deleteItemOrder(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public List<ItemOrder> getAll(){
        return itemOrderMapper.queryAllItemOrder();
    }

    public ItemOrder getReceipt(int id){
        return itemOrderMapper.queryItemOrderByItemOrderId(id);
    }

}
