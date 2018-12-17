package com.lee.vo;

import com.lee.entity.News;

/**
 * @author lee
 **/
public class NewsVo extends News {

    private String username;

    private String headurl;

    private Integer commentcount;

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

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }
}
