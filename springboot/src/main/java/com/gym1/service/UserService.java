package com.gym1.service;


import com.gym1.entity.Item;
import com.gym1.entity.User;
import com.gym1.entity.Venue;
import com.gym1.entity.VenueType;
import com.gym1.mapper.*;
import com.gym1.util.EmailUtil;
import com.gym1.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.*;



@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VenueTypeMapper venueTypeMapper;

    @Autowired
    private VenueMapper venueMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemOrderMapper itemOrderMapper;


    public int registerService(Map map){
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        password = EncryptionUtil.encryption(password);
        String phoneNumber = map.get("phoneNumber").toString();
        String name = map.get("name").toString();
        String email = map.get("email").toString();
        User user = new User(username, password, phoneNumber, name, email);
        String sex = map.get("sex").toString();
        String age = map.get("age").toString();
        if (age != null){
            user.setAge(Integer.parseInt(age));
        }else{
            user.setAge(-1);
        }
        if (sex.equals("None")){
            user.setSex(-1);
        }else if (sex.equals("Female")){
            user.setSex(0);
        } else if (sex.equals("Male")) {
            user.setSex(1);
        }else{
            user.setSex(-1);
        }
        int res = 0;
        try{
            res = userMapper.addUser(user);
            return res;
        }catch (Exception e){
            return -1;
        }
    }


    public Map<String, User> updateUserInfo(int id, Map<String, String> map){
        String password = map.get("password");
        password = EncryptionUtil.encryption(password);
        String phoneNumber = map.get("phoneNumber");
        String name = map.get("name");
        User user = new User(id, password, phoneNumber, name);
        String sex = map.get("sex");
        String age = map.get("age");
        if (age != null){
            user.setAge(Integer.parseInt(age));
        }else{
            user.setAge(-1);
        }
        if (sex.equals("None")){
            user.setSex(-1);
        }else if (sex.equals("Female")){
            user.setSex(0);
        } else if (sex.equals("Male")) {
            user.setSex(1);
        }else{
            user.setSex(-1);
        }
        int res = 0;
        try{
            res = userMapper.editUser(user);
        }catch (Exception e){
            res = -1;
        }
        User user1 = new User(res);
        Map<String, User> reMap = new HashMap<>();
        reMap.put("user", user);
        reMap.put("res", user1);
        return reMap;
    }

    public User loginService(String username){
        return userMapper.queryUserByUsername(username);
    }

    public List<User> getAllUser(){
        return userMapper.queryAllUser();
    }

    public User getUserInfo(int id){
        return userMapper.queryUserById(id);
    }

    public int updateProfile(int id, String image){
        int res = 0;
        try{
            res = userMapper.updateUserImage(id, image);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public User adminLoginService(String username){
        return userMapper.queryUserByUsername2(username);
    }

    public User rootLoginService(String username){
        return userMapper.queryUserByUsername3(username);
    }

    public int manageAdmin(int id, int num){
        int res = 0;
        try{
            res = userMapper.updateUserAdminById(id, num);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public int updateCard(String card, int id){
        int res = 0;
        try{
            res = userMapper.updateUserCardById(id, card);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public int subscribe(int id, int num){
        User user = userMapper.queryUserById(id);
        int res = 0;
        if (user.getIsMember() == 1){
            Date date = user.getMembership();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, num);
            date = calendar.getTime();
            try{
                res = userMapper.updateUserMembershipById(id, date);
                return res;
            }catch (Exception e){
                return -1;
            }
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, num);
            Date date = calendar.getTime();
            try{
                res = userMapper.updateUserMembershipById(id, date);
                return res;
            }catch (Exception e){
                return -1;
            }
        }
    }

    public String verifyUsername(String username, String email){
        if(userMapper.queryUserByUsername(username) != null){
            return "0";
        }else{
            try{
                String code = "";
                Random random = new Random();
                for (int i = 0; i < 6; i++) {
                    code = code + random.nextInt(10);
                }
                String content = EmailUtil.codeEmail(code);
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject("Verification Code");
                mimeMessageHelper.setText(content, true);
                mailSender.send(message);
                return code;
            }catch (Exception e){
                return "1";
            }
        }
    }


    public Map<String, String> getVenueProportion(){
        Map<String, String> reMap = new HashMap<>();
        List<VenueType> venueType = venueTypeMapper.queryAll();
        System.out.println(venueType);
        for (VenueType type : venueType){
            int number = venueMapper.countVenueTypeNumber(type.getId());
            reMap.put(type.getTypeName(), number+"");
        }
        return  reMap;
    }


    public Map<String, String> getVenueBooking(){
        Map<String, String> reMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == -1){
            day = 7;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1-day);
        Date date = calendar1.getTime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        String week = formatter.format(date).substring(0,11) + "00:00:00";
        List<Venue> venues = venueMapper.queryAllVenue();
        for (Venue venue : venues){
            int number = orderMapper.countNumberByVenueId(venue.getId(), week);
            reMap.put(venue.getName(), number+"");
        }
        return  reMap;
    }


    public Map<String, String> getItemBooking(){
        Map<String, String> reMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == -1){
            day = 7;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1-day);
        Date date = calendar1.getTime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        String week = formatter.format(date).substring(0,11) + "00:00:00";
        List<Item> items = itemMapper.queryAllItem();
        for (Item item : items){
            int number = itemOrderMapper.countNumberByItemId(item.getId(), week);
            reMap.put(item.getItemName(), number+"");
        }
        return  reMap;
    }


    public Map<String, String> getWeeklyTendency(){
        Map<String, String> reMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == -1){
            day = 7;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1-day);
        int temp = 1-day;
        Date date = calendar1.getTime();
        List<String> time = new ArrayList<>();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        for (int i = 0; i < 6; i++){
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, temp-7*i);
            date = calendar.getTime();
            time.add(i, formatter.format(date).substring(0,11) + "00:00:00");
        }
        double res = 0;
        double a;
        double b;
        for (int i = 0; i < 6; i++){
            res = 0;
            if (i == 0){
                List<User> users = userMapper.queryAllUser();
                for (User user : users){
                    if (user.getIsMember() == 1){
                        res += 199;
                    }
                }
                a = itemOrderMapper.sumPriceByDate2(time.get(i));
                b = orderMapper.sumPriceByDate2(time.get(i));
                res = res + a + b;
                reMap.put(i+"", res+"");
            }else{
                a = itemOrderMapper.sumPriceByDate(time.get(i), time.get(i-1));
                b = orderMapper.sumPriceByDate(time.get(i), time.get(i-1));
                res = res + a + b;
                reMap.put(i+"", res+"");
            }
        }
        return reMap;
    }


}
