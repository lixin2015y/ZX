package com.lee.controller;

import com.lee.api.MessageService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.constant.ZxException;
import com.lee.entity.User;
import com.lee.model.HostHolder;
import com.lee.vo.MessageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author lee
 */

@RequestMapping("message")
@RestController
public class MessageController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    MessageService messageService;

    @PostMapping("getMessage")
    ResponseMessage getMessage() {

        if (hostHolder.getUser() == null) {
            return Result.error("未登录");
        }

        List<MessageVo> messageVoList = messageService.getMessage(hostHolder.getUser().getId());

        return Result.success(messageVoList);
    }

    @PostMapping("deleteMessage")
    ResponseMessage deleteMessage(String messageId) {

        if (StringUtils.isBlank(messageId)) {
            return Result.error("参数为空");
        }

        try {
            messageService.deleteMessage(messageId);
        } catch (ZxException e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    @PostMapping("deleteAllMessage")
    ResponseMessage deleteAllMessage() {

        User user = hostHolder.getUser();

        if (user == null) {
            return Result.error("未登录");
        }

        try {
            messageService.deleteAllMessage(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

}
