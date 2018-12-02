package com.lee.dao;

import com.lee.entity.User;

public interface ZxDao {

    int checkEmail(String email);

    int addUser(User user);

    int checkUser(User user);

    User selectUserByEmail(String email);
}
