package com.lee.vo;

import com.lee.entity.News;

/**
 * @author lee
 */
public class UserNewsVo extends News {

    private Integer commentcount;

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }
}
