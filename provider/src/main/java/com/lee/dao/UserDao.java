package com.lee.dao;

import com.lee.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    int checkEmail(@Param("email") String email,@Param("id") String id);

    int addUser(User user);

    int checkUser(User user);

    User selectUserByEmail(String email);

    int updateUserTicket(@Param("email") String email, @Param("ticket") String ticket);

    User selectUserByTicket(String ticket);

    int updateUserTicketByTicket(String ticket);

    int updateUserInfo(User user);
}
