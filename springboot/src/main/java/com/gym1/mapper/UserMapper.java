package com.gym1.mapper;

import com.gym1.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUser(User user);
    List<User> queryAllUser();
    User queryUserByUsername(String username);
    int editUser(String username);


}
