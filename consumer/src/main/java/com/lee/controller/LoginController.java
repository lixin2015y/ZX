package com.lee.controller;

import com.lee.api.LoginService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.entity.User;
import com.lee.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;


/**
 * @author lee
 */
@RequestMapping("login")
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @PostMapping("register")
    public ResponseMessage register(@RequestBody User user, HttpServletResponse response) {

        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())) {
            return Result.error("必要参数为空");
        }

        if (loginService.checkEmail(user.getEmail())) {
            return Result.error("用户已被注册");
        }

        user.setId(Utils.getUUID(32));
        user.setJointime(new Date());
        user.setSalt(Utils.getUUID(6));
        if (StringUtils.isBlank(user.getUsername())) {
            user.setUsername(user.getEmail().split("@")[0]);
        }
        user.setPassword(Utils.MD5(user.getPassword() + user.getSalt()));

        try {
            loginService.addUser(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        String ticket = Utils.getUUID(20);

        Cookie cookie = new Cookie("ticket", ticket);

        cookie.setPath("/");

        cookie.setMaxAge(3600 * 24 * 5);

        response.addCookie(cookie);

        return Result.success("注册成功");
    }

    @PostMapping("login")
    public ResponseMessage login(@RequestBody User user) {

        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())) {
            return Result.error("必要参数为空");
        }

        if (!loginService.checkEmail(user.getEmail())) {
            return Result.error("该邮箱不存在");
        }

        User u = loginService.selectUserByEmail(user.getEmail());

        if (!Utils.MD5(user.getPassword() + u.getSalt()).equals(u.getPassword())) {
            return Result.error("密码错误");
        }

        //ticket


        return Result.success();
    }

}
