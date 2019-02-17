package com.lee.api;

import com.lee.vo.MessageVo;

import java.util.List;

/**
 * @author lee
 */
public interface MessageService {

    List<MessageVo> getMessage(String userId);

}
