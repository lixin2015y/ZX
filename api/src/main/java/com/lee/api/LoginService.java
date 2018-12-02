package com.lee.api;

import com.lee.entity.User;

/**
 * @author lee
 */
public interface LoginService {

    boolean checkEmail(String email);

    void addUser(User user);

    boolean checkUser(User user);

    User selectUserByEmail(String email);
}