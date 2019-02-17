package com.lee.service;

import com.lee.api.MessageService;
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

        return null;
    }

}
