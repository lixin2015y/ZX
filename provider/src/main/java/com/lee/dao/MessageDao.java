package com.lee.dao;

import com.lee.entity.Message;
import com.lee.vo.MessageVo;

import java.util.List;

/**
 * @author lee
 **/
public interface MessageDao {

    List<MessageVo> getMessage(String userId);

    int addMessage(Message message);

    int deleteMessage(String messageId);

    int deleteAllMessage(String toId);
}
