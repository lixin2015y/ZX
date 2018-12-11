package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.entity.User;

/**
 * @author lee
 */
public interface UserService {

    void updateUserInfo(User user) throws ZxException;

}
