package com.lee.service;

import com.lee.api.UserService;
import com.lee.constant.ZxException;
import com.lee.dao.UserDao;
import com.lee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void updateUserInfo(User user) throws ZxException {
        if (userDao.updateUserInfo(user) != 1) {
            throw new ZxException("删除数据异常");
        }
    }
}
