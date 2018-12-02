package com.lee.model;

import com.lee.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author lee
 */
@Component
public class HostHolder {

    private ThreadLocal<User> user = new ThreadLocal<User>();

    public ThreadLocal<User> getUser() {
        return user;
    }

    public void setUser(ThreadLocal<User> user) {
        this.user = user;
    }

    public void clear() {
        user.remove();
    }
}
