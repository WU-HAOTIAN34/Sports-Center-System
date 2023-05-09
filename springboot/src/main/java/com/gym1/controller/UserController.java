package com.gym1.controller;


import com.gym1.entity.User;
import com.gym1.service.UserService;
import com.gym1.util.EncryptionUtil;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;



@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String from;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User res = userService.loginService(username);
        Map<String, Object> reMap = new HashMap<>();
        if (res == null){
            reMap.put("code", 7001);
            reMap.put("message", "Username doesn't exist!");
            return reMap;
        }else if(EncryptionUtil.decrypt(res.getPassword()).equals(password)){
            String id = res.getId() + "";
            String token = JwtUtil.getJwtToken(id, username, password);
            reMap.put("code", 7002);
            reMap.put("message", "Login successfully!");
            reMap.put("data", token);
            return reMap;
        }else{
            reMap.put("code", 7003);
            reMap.put("message", "Password isn't correct!");
            return reMap;
        }
    }


    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int res = userService.registerService(map);
        if (res == -1){
            reMap.put("code", 7005);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7006);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7007);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/updateInfo")
    public Map<String, Object> updateInfo(HttpServletRequest request, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        Map<String, User> temp = userService.updateUserInfo(id, map);
        User user = temp.get("user");
        String token = JwtUtil.getJwtToken(user.getId()+"", user.getUsername(), user.getPassword());
        int res = temp.get("res").getId();
        if (res == -1 ){
            reMap.put("code", 7008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 7009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7010);
            reMap.put("msg", "Success!");
            reMap.put("data", token);
        }
        return reMap;
    }

    @GetMapping("/getUserInfo")
    public Map<String, Object> getUserInfo(HttpServletRequest request){
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        User user = userService.getUserInfo(id);
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", 7011);
        reMap.put("msg", "Success!");
        reMap.put("data", user);
        reMap.put("card", user.getCard());
        return reMap;
    }


    @PostMapping("/updateProfile")
    public Map<String, Object> updateProfile(HttpServletRequest request, @RequestBody Map<String,String> map){
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String image = map.get("upload");
        int res = userService.updateProfile(id, "data:image/png;base64,"+image);
        Map<String, Object> reMap = new HashMap<>();
        if (res == -1){
            reMap.put("code", 7012);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7013);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7014);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/admin/login")
    public Map<String, Object> adminLogin(@RequestBody Map map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User res = userService.adminLoginService(username);
        Map<String, Object> reMap = new HashMap<>();
        if (res == null){
            reMap.put("code", 7015);
            reMap.put("message", "Username doesn't exist!");
            return reMap;
        }else if(EncryptionUtil.decrypt(res.getPassword()).equals(password)){
            String id = res.getId() + "";
            String token = JwtUtil.getJwtToken(id, username, password);
            reMap.put("code", 7016);
            reMap.put("message", "Login successfully!");
            reMap.put("data", token);
            return reMap;
        }else{
            reMap.put("code", 7017);
            reMap.put("message", "Password isn't correct!");
            return reMap;
        }
    }


    @PostMapping("/root/login")
    public Map<String, Object> rootLogin(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User res = userService.rootLoginService(username);
        Map<String, Object> reMap = new HashMap<>();
        if (res == null) {
            reMap.put("code", 7018);
            reMap.put("message", "Username doesn't exist!");
            return reMap;
        } else if (EncryptionUtil.decrypt(res.getPassword()).equals(password)) {
            String id = res.getId() + "";
            String token = JwtUtil.getJwtToken(id, username, password);
            reMap.put("code", 7019);
            reMap.put("message", "Login successfully!");
            reMap.put("data", token);
            return reMap;
        } else {
            reMap.put("code", 7020);
            reMap.put("message", "Password isn't correct!");
            return reMap;
        }
    }


    @GetMapping("/admin/getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> reMap = new HashMap<>();
        List<User> res = userService.getAllUser();
        if (res.size() != 0){
            reMap.put("code", 7021);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7022);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/root/manageAdmin/{id}")
    public Map<String, Object> manageAdmin(@PathVariable int id, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int num = Integer.parseInt(map.get("num").toString());
        int res = userService.manageAdmin(id, num);
        if (res == -1){
            reMap.put("code", 7023);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7024);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7025);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/addCard")
    public Map<String, Object> manageAdmin(HttpServletRequest request, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        String card = map.get("card").toString();
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        int res = userService.updateCard(card, id);
        if (res == -1){
            reMap.put("code", 7026);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7027);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7028);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteCard")
    public Map<String, Object> manageAdmin(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        int res = userService.updateCard(null, id);
        if (res == -1){
            reMap.put("code", 7029);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7030);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7031);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/subscribe")
    public Map<String, Object> subscribe(HttpServletRequest request, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int id = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        int number = Integer.parseInt(map.get("num").toString());
        int res = userService.subscribe(id, number);
        if (res == -1){
            reMap.put("code", 7032);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 7033);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7034);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/verifyUsername")
    public Map<String, Object> verifyUsername(@RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        String email = map.get("email").toString();
        String username = map.get("username").toString();
        String res = userService.verifyUsername(username, email);
        if (res.equals("0")){
            reMap.put("code", 7004);
            reMap.put("msg", "The username has existed!");
            reMap.put("data", res);
        }else if(res.equals("1")){
            reMap.put("code", 7035);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else{
            res= EncryptionUtil.encryption(res);
            reMap.put("code", 7036);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/root/getVenueProportion")
    public Map<String, Object> getVenueProportion(){
        Map<String, Object> reMap = new HashMap<>();
        Map<String, String> res = userService.getVenueProportion();
        if (res.size() != 0){
            reMap.put("code", 7037);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7038);
            reMap.put("msg", "There are no venues!");
            reMap.put("data", res);
        }
        return reMap;
    }

    @GetMapping("/root/getVenueBooking")
    public Map<String, Object> getVenueBooking(){
        Map<String, Object> reMap = new HashMap<>();
        Map<String, String> res = userService.getVenueBooking();
        if (res.size() != 0){
            reMap.put("code", 7039);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7040);
            reMap.put("msg", "There are no venues!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/root/getItemBooking")
    public Map<String, Object> getItemBooking(){
        Map<String, Object> reMap = new HashMap<>();
        Map<String, String> res = userService.getItemBooking();
        if (res.size() != 0){
            reMap.put("code", 7041);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 7042);
            reMap.put("msg", "There are no items!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/root/getWeeklyTendency")
    public Map<String, Object> getWeeklyTendency(){
        Map<String, Object> reMap = new HashMap<>();
        Map<String, String> res = userService.getWeeklyTendency();
        reMap.put("code", 7043);
        reMap.put("msg", "Success!");
        reMap.put("data", res);
        return reMap;
    }

}
