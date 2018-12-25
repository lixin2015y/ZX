package com.lee.vo;

import com.lee.entity.Comment;
import com.lee.entity.User;

import java.util.List;

/**
 * @author lee
 **/
public class CommentVo extends Comment {

    private User user;

    private List<Comment> commentVoList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentVoList() {
        return commentVoList;
    }

    public void setCommentVoList(List<Comment> commentVoList) {
        this.commentVoList = commentVoList;
    }
}
