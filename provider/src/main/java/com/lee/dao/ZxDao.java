package com.lee.dao;

import com.lee.entity.User;
import org.apache.ibatis.annotations.Param;

public interface ZxDao {

    int checkEmail(String email);

    int addUser(User user);

    int checkUser(User user);

    User selectUserByEmail(String email);
    
    int updateUserTicket(@Param("email") String email, @Param("ticket") String ticket);

    User selectUserByTicket(String ticket);
}
