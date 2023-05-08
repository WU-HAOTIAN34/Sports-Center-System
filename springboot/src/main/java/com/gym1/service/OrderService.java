package com.gym1.service;


import com.gym1.entity.Order;
import com.gym1.entity.User;
import com.gym1.entity.Venue;
import com.gym1.entity.VenueState;
import com.gym1.mapper.OrderMapper;
import com.gym1.mapper.UserMapper;
import com.gym1.mapper.VenueMapper;
import com.gym1.mapper.VenueStateMapper;
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


@Service
public class OrderService {

    @Autowired
    private VenueStateMapper venueStateMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VenueMapper venueMapper;

    public int addOrder(int id, String userId, String status) throws MessagingException {
        int uId = Integer.parseInt(userId);
        VenueState venueState = venueStateMapper.queryVenueStateById(id);
        int venueId = venueStateMapper.queryVenueIdByVenueStateId(id);
        Venue venue = venueMapper.queryVenueById(venueId);
        if (venueState.getOpen() == 1 && venueState.getFree() == 1){
            int a = 0;
            int b = 0;
            try{
                a = venueStateMapper.updateVenueStateFree(id, 0);
                if (a==0){
                    return -1;
                }else{
                    try{
                        Date date = new Date(System.currentTimeMillis());
                        double price = venue.getPrice();
                        if (status.equals("user")){
                            User user = userMapper.queryUserById(uId);
                            if (user.getIsMember() == 1){
                                int t = (int)(price * 0.75 * 100);
                                price = (double)(t / 100);
                            }
                        }
                        Order order = new Order(id, uId, date, price);
                        b = orderMapper.addOrder(order);
                        if (b != 0){
                            int oId = orderMapper.queryOrderIdByOrder(order);
                            Order order1 = orderMapper.queryOrderById(oId);
                            MimeMessage message = mailSender.createMimeMessage();
                            MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
                            minehelper.setFrom(from);
                            User user = userMapper.queryUserById(uId);
                            minehelper.setTo(user.getEmail());
                            minehelper.setSubject("Order Successfully");
                            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
                            String year = formatter.format(date).substring(0,4);
                            String month = formatter.format(date).substring(5,7);
                            String day = formatter.format(date).substring(8,10);
                            String begin = formatter.format(order1.getBegin());
                            String end = formatter.format(order1.getEnd());
                            String content = EmailUtil.orderEmail(year, month, day, oId, order1.getUserName(),
                                    order1.getPhoneNumber(), order1.getUsername(), order1.getName(), order1.getAddress(),
                                    begin, end, order1.getPrice()+"", formatter.format(date));
                           minehelper.setText(content, true);
                           mailSender.send(message);
                        }
                        return b;
                    }catch (Exception e1){
                        venueStateMapper.updateVenueStateFree(id, 1);
                        return -1;
                    }
                }
            }catch (Exception e){
                return -1;
            }
        }else{
            return -2;
        }
    }

    public List<Order> queryUserOrder(int id){
        List<Order> orders = orderMapper.queryOrderByUserId(id);
        Date date = new Date(System.currentTimeMillis());
        for (Order order : orders){
            if (date.compareTo(order.getBegin()) != -1){
                order.setUserId(0);
            }else{
                order.setUserId(1);
            }
        }
        return orders;
    }

    public List<Order> queryUserUncommentOrder(int id){
        List<Order> orders = orderMapper.queryOrderByUserIdComment(id);
        Date date = new Date(System.currentTimeMillis());
        for (Order order : orders){
            if (date.compareTo(order.getBegin()) != -1){
                order.setUserId(0);
            }else{
                order.setUserId(1);
            }
        }
        return orders;
    }

    public int deleteOrder(int id){
        int venueStateId = orderMapper.queryVenueStateIdByOrderId(id);
        int a = 0;
        int res = 0;
        try{
            a = venueStateMapper.updateVenueStateFree(venueStateId, 1);
            if (a == 0){
                return 0;
            }else{
                try{
                    res = orderMapper.deleteOrder(id);
                    if (res == 0){
                        venueStateMapper.updateVenueStateFree(venueStateId, 0);
                        return 0;
                    }else{
                        return res;
                    }
                }catch (Exception e){
                    venueStateMapper.updateVenueStateFree(venueStateId, 0);
                    return -1;
                }
            }
        }catch (Exception e){
            return -1;
        }
    }

    public List<Order> getAll(){
        return orderMapper.queryAllOrder();
    }

    public Order getReceipt(int id){
        return orderMapper.queryOrderById(id);
    }

}
