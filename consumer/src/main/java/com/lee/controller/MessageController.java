package com.lee.controller;

import com.lee.api.MessageService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.model.HostHolder;
import com.lee.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("getMessage")
    public ResponseMessage getMessage() {

        if (hostHolder.getUser() == null) {
            return Result.error("未登录");
        }

        List<MessageVo> messageVoList = messageService.getMessage(hostHolder.getUser().getId());

        return Result.success(messageVoList);
    }


}
