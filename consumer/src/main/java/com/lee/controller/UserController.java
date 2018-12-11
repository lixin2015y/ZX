package com.lee.controller;

import com.google.gson.Gson;
import com.lee.api.LoginService;
import com.lee.api.UserService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.entity.User;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
    ResponseMessage updateUserHeadUrl(MultipartFile file) {
        return Result.success();
    }
}
