package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.entity.User;

/**
 * @author lee
 */
public interface LoginService {

    boolean checkEmail(String email);

    void addUser(User user);

    boolean checkUser(User user);

    User selectUserByEmail(String email);

    void updateUserTicket(String email, String ticket) throws ZxException;

    User selectUserByTicket(String ticket);

}
