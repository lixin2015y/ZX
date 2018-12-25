package com.lee.vo;

import com.lee.entity.News;
import com.lee.entity.User;

import java.util.List;

/**
 * @author lee
 **/
public class NewsDetailVo extends News {

    private User user;

    private List<CommentVo> commentVoList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CommentVo> getCommentVoList() {
        return commentVoList;
    }

    public void setCommentVoList(List<CommentVo> commentVoList) {
        this.commentVoList = commentVoList;
    }
}
