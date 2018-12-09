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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author lee
 */
@RequestMapping("login")
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    RedisTemplate redisTemplate;

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

        String userId = Utils.getUUID(32);
        String ticket = Utils.getUUID(20);
        user.setId(userId);
        user.setJointime(new Date());
        user.setSalt(Utils.getUUID(6));
        user.setTicket(ticket);
        if (StringUtils.isBlank(user.getUsername())) {
            user.setUsername(user.getEmail().split("@")[0]);
        }
        user.setPassword(Utils.MD5(user.getPassword() + user.getSalt()));

        try {
            loginService.addUser(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }


        Cookie cookie = new Cookie("ticket", ticket);

        cookie.setPath("/");

        cookie.setMaxAge(3600 * 24 * 5);

        response.addCookie(cookie);

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(ticket, user, 3600 * 24 * 5, TimeUnit.SECONDS);

        return Result.success("注册成功");
    }

    @PostMapping("login")
    public ResponseMessage login(@RequestBody User user, HttpServletResponse response) {

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

        String ticket = Utils.getUUID(20);

        try {
            loginService.updateUserTicket(user.getEmail(), ticket);
        } catch (Exception e) {
            Result.error(e.getMessage());
        }

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(ticket, u, 3600 * 24 * 5, TimeUnit.SECONDS);

        Cookie cookie = new Cookie("ticket", ticket);

        cookie.setPath("/");

        cookie.setMaxAge(3600 * 24 * 5);

        response.addCookie(cookie);

        return Result.success("登陆成功");

    }

    @PostMapping("getUserInfoByTicket")
    ResponseMessage getUserInfo(@CookieValue("ticket") String ticket, HttpServletResponse response) {

        User user = new User();

        if (StringUtils.isBlank(ticket)) {
            try {
                response.sendRedirect("/html/user/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            user = loginService.selectUserByTicket(ticket);
            if (user == null) {
                try {
                    response.sendRedirect("/html/user/login.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success(user);
    }


    @RequestMapping("logout")
    ResponseMessage logout(@CookieValue("ticket") String ticket,HttpServletResponse response) {
        if (StringUtils.isBlank(ticket)) {
            return Result.error();
        }
        redisTemplate.delete(ticket);
        try {
            response.sendRedirect("/html/user/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
