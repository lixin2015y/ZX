package com.lee.service;

import com.lee.api.LoginService;
import com.lee.constant.ZxException;
import com.lee.dao.UserDao;
import com.lee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lee
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    public boolean checkEmail(String email, String id) {
        if (userDao.checkEmail(email,id) > 0) {
            return true;
        }
        return false;
    }


    public void addUser(User user) {
        userDao.addUser(user);
    }

    public boolean checkUser(User user) {
        if (userDao.checkUser(user) > 0) {
            return true;
        }
        return false;
    }

    public User selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    public void updateUserTicket(String email, String ticket) throws ZxException {
        int i = userDao.updateUserTicket(email, ticket);
        if (i != 1) {
            throw new ZxException("ticket信息修改异常" + i);
        }
    }

    public User selectUserByTicket(String ticket) {
        return userDao.selectUserByTicket(ticket);
    }

    public void updateUserTicketByTicket(String ticket) throws ZxException {

        if (userDao.updateUserTicketByTicket(ticket) != 1) {
            throw new ZxException("更新ticket失败");
        }
    }

}
