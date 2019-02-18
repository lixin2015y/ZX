package com.lee.service;

import com.lee.api.MessageService;
import com.lee.constant.ZxException;
import com.lee.dao.MessageDao;
import com.lee.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lee
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageDao messageDao;

    @Override
    public List<MessageVo> getMessage(String userId) {
        return messageDao.getMessage(userId);
    }

    @Override
    public void deleteMessage(String messageId) throws ZxException {
        if (messageDao.deleteMessage(messageId) != 1) {
            throw new ZxException("删除错误");
        }
    }

    @Override
    public void deleteAllMessage(String toId) {
        messageDao.deleteAllMessage(toId);
    }
}
