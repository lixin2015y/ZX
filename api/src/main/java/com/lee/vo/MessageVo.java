package com.lee.vo;

import com.lee.entity.Message;

/**
 * @author lee
 */
public class MessageVo extends Message {

    private String username;

    private String userid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
