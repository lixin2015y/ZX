package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.vo.MessageVo;

import java.util.List;

/**
 * @author lee
 */
public interface MessageService {

    List<MessageVo> getMessage(String userId);

    void deleteMessage(String messageId) throws ZxException;

    void deleteAllMessage(String toId);

}
