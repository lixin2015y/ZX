package com.lee.vo;

import com.lee.entity.Comment;
import com.lee.entity.User;

import java.util.List;

/**
 * @author lee
 **/
public class CommentVo extends Comment {

    private String username;

    private String headurl;

    private List<CommentVo> commentVoList;


    public List<CommentVo> getCommentVoList() {
        return commentVoList;
    }

    public void setCommentVoList(List<CommentVo> commentVoList) {
        this.commentVoList = commentVoList;
    }

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
