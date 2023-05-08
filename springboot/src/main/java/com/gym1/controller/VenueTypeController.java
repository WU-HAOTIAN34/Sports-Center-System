package com.gym1.controller;


import com.gym1.service.VenueTypeService;
import com.gym1.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/venueType")
public class VenueTypeController {

    @Autowired
    private VenueTypeService venueTypeService;


    @GetMapping("/getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> reMap = new HashMap<>();
        List<String> res = venueTypeService.getAll();
        reMap.put("code", 1101);
        reMap.put("msg", "Success!");
        reMap.put("data", res);
        return reMap;
    }


    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/mail")
    public Map<String, Object> commonEmail(@RequestBody Map map) throws MessagingException {

        Map<String, Object> reMap = new HashMap<>();
        //创建简单邮件消息

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper minehelper = new MimeMessageHelper(message, true);
        //谁发的
        minehelper.setFrom(from);
        //谁要接收
        minehelper.setTo(map.get("a").toString());
        //邮件标题
        //minehelper.setSubject(map.get("b").toString());
        //邮件内容
        //String c = EmailUtil.orderEmail();
        minehelper.setText(null, true);
        try {
            mailSender.send(message);
            reMap.put("code", 1101);
            reMap.put("msg", "Success!");
            reMap.put("data", "发送普通邮件成功");

        } catch (MailException e) {
            e.printStackTrace();
            reMap.put("code", 1101);
            reMap.put("msg", "Success!");
            reMap.put("data", "shibai");
        }
        return reMap;
    }


}