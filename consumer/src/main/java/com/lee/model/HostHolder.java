package com.lee.model;

import com.lee.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/13 15:42
 * @description TODO
 **/
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<User>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
