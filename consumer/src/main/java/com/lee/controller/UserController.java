package com.lee.controller;

import com.lee.api.LoginService;
import com.lee.api.UserService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.entity.User;
import com.lee.util.QiniuUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lee
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("updateUserInfo")
    ResponseMessage updateUserInfo(@RequestBody User user) {

        if (loginService.checkEmail(user.getEmail(), user.getId())) {
            return Result.error("邮箱已被注册");
        }

        try {
            userService.updateUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }


    @PostMapping("updateUserHeadUrl")
    ResponseMessage updateUserHeadUrl(@RequestParam("file") MultipartFile file,@CookieValue(name = "ticket", required = false) String ticket) {

        if (StringUtils.isBlank(ticket)) {
            return Result.error();
        }

        String headurl = "";

        try {
            headurl = QiniuUtil.upload(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }


        try {
            User user = new User();
            user.setTicket(ticket);
            user.setHeadurl(headurl);
            userService.updateUserHeadUrl(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }


}
