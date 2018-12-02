package com.lee.service;

import com.lee.api.LoginService;
import com.lee.dao.ZxDao;
import com.lee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lee
 */
@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    ZxDao zxDao;

    public boolean checkEmail(String email) {
        if (zxDao.checkEmail(email) > 0) {
            return true;
        }
        return false;
    }


    public void addUser(User user) {
        zxDao.addUser(user);
    }

    public boolean checkUser(User user) {
        if (zxDao.checkUser(user) > 0) {
            return true;
        }
        return false;
    }

    public User selectUserByEmail(String email) {
        return zxDao.selectUserByEmail(email);
    }
}
