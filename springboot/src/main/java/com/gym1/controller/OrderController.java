package com.gym1.controller;


import com.gym1.entity.Comment;
import com.gym1.entity.Order;
import com.gym1.service.OrderService;
import com.gym1.util.JwtUtil;
import com.gym1.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/makeOrder/{venueStateId}")
    public Map<String, Object> makeOrder(HttpServletRequest request, @PathVariable int venueStateId,
                                         @RequestBody Map map) throws MessagingException {
        Map<String, Object> reMap = new HashMap<>();
        String status = map.get("status").toString();
        int res = orderService.addOrder(venueStateId, JwtUtil.getMemberIdByJwtToken(request), status);
        if(res == -2){
            reMap.put("code", 5001);
            reMap.put("message", "Failure!");
            reMap.put("data", res);
        }else if(res == -1){
            reMap.put("code", 5002);
            reMap.put("message", "Error!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 5003);
            reMap.put("message", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getAllOrder")
    public Map<String, Object> getAllOrder(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<Order> res = orderService.queryUserOrder(uId);
        if (res.size() != 0){
            reMap.put("code", 5004);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 5005);
            reMap.put("msg", "There are no orders!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getAllUncommentOrder")
    public Map<String, Object> getAllUncommentOrder(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<Order> res = orderService.queryUserUncommentOrder(uId);
        if (res.size() != 0){
            reMap.put("code", 5006);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 5007);
            reMap.put("msg", "There are no orders!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteOrder/{id}")
    public Map<String, Object> deleteOrder(@PathVariable int id){
        Map<String, Object> reMap = new HashMap<>();
        int res = orderService.deleteOrder(id);
        if (res == -1){
            reMap.put("code", 5008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 5009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 5010);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }

    @GetMapping("/admin/getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> reMap = new HashMap<>();
        List<Order> res = orderService.getAll();
        if (res.size() != 0){
            reMap.put("code", 5011);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 5012);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getReceipt/{orderId}")
    public Map<String, Object> getReceipt(@PathVariable int orderId){
        Map<String, Object> reMap = new HashMap<>();
        Order order = orderService.getReceipt(orderId);
        String content = "{\"Name\":" + " \"" + order.getUserName() + "\"," +
                " \"Phone Number:\"" + " \"" + order.getPhoneNumber() + "\"," +
                " \"Username:\"" + " \"" + order.getUsername() + "\"}";
        String code = QRCodeUtil.createQRCode(content);
        if (code.equals("Failure")){
            reMap.put("code", 5013);
            reMap.put("msg", "Failure!");
            reMap.put("data", order);
            reMap.put("QRCode", code);
        }else{
            reMap.put("code", 5014);
            reMap.put("msg", "Success!");
            reMap.put("data", order);
            reMap.put("QRCode", code);
        }
        return reMap;
    }

}
