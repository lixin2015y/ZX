package com.lee.vo;

import com.lee.entity.News;

/**
 * @author lee
 **/
public class NewsDetailVo extends News {

    private String username;

    private String headurl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
}
