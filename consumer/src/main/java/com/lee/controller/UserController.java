package com.lee.controller;

import com.lee.api.LoginService;
import com.lee.api.UserService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.constant.ZxException;
import com.lee.entity.User;
import com.lee.model.HostHolder;
import com.lee.util.QiniuUtil;
import com.lee.utils.Utils;
import com.lee.vo.ResetPasswordVo;
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

    @Autowired
    HostHolder hostHolder;

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
    ResponseMessage updateUserHeadUrl(@RequestParam("file") MultipartFile file, @CookieValue(name = "ticket", required = false) String ticket) {

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

    @PostMapping("resetPassword")
    ResponseMessage resetPassword(@RequestBody ResetPasswordVo resetPasswordVo) {

        User user = hostHolder.getUser();

        if (StringUtils.isBlank(resetPasswordVo
                .getPassword()) || StringUtils.isBlank(resetPasswordVo.getOldpassword())) {
            return Result.error("密码不能为空");
        }

        if (!Utils.MD5(resetPasswordVo.getOldpassword() + user.getSalt()).equals(user.getPassword())) {
            return Result.error("密码错误");
        }

        User newUser = new User();

        newUser.setId(user.getId());

        newUser.setPassword(Utils.MD5(resetPasswordVo.getPassword() + user.getSalt()));

        try {
            userService.updateUserInfo(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改失败");
        }

        return Result.success("修改密码成功");
    }


}
