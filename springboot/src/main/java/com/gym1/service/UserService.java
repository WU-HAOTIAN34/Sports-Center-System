package com.gym1.service;


import com.gym1.entity.User;
import com.gym1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int registerService(Map<String, String> map){
//        get parameters
        String username = map.get("username");
        String password = map.get("password");
        String phoneNumber = map.get("phoneNumber");
        String name = map.get("name");
        String email = map.get("email");
        User user = new User(username, password, phoneNumber, name, email);
        String sex = map.get("sex");
        String age = map.get("age");
//        judge if the potential parameters are entered
        if(age != null || !sex.equals("None")){
            if(age == null){
                int temp = 1;
                if(sex.equals("Female")){
                    temp = 0;
                }
                user.setSex(temp);
                user.setAge(-1);
            } else if (sex.equals("None")) {
                user.setAge(Integer.parseInt(age));
            }else{
                int temp = 1;
                if(sex.equals("Female")){
                    temp = 0;
                }
                user.setSex(temp);
                user.setAge(Integer.parseInt(age));
            }
        }
//        judge if the username is existed
        if(userMapper.queryUserByUsername(username) == null){
            return -1;
        }else{
            return userMapper.addUser(user);
        }
    }

    public User loginService(String username){
        return userMapper.queryUserByUsername(username);
    }



}
