package com.gym1.controller;

import com.gym1.entity.User;
import com.gym1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public int login(HttpServletRequest request, @RequestBody Map map){

        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User res = userService.loginService(username);
        if (res == null){
            return 0;
        }else if(res.getPassword().equals(password)){
            HttpSession session = request.getSession();
            if(session.getAttribute("user") != null){
                session.removeAttribute("user");
            }
            session.setAttribute("user", res.getPassword());
            System.out.println(session.getAttribute("user"));
            return 1;
        }else{
            return -1;
        }
    }

    @PostMapping("/register")
    public int register(@RequestParam Map<String,String> map){
        return userService.registerService(map);
    }

    @PostMapping("/logOut")
    public int logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return 1;
    }

    @PostMapping("/ifLogin")
    public int register(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return 0;
        }else{
            return 1;
        }
    }

    @GetMapping("/getSession/{id}")
    public String getSession(@PathVariable Integer id){
        System.out.println(id);
        return "11111";
    }

}
