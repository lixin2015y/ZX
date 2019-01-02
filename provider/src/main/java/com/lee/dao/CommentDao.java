package com.lee.dao;

import com.lee.entity.Comment;
import com.lee.vo.CommentVo;

import java.util.List;

/**
 * @author lee
 **/
public interface CommentDao {

    List<CommentVo> getCommentList(String newsId);

    int addComment(Comment comment);
}
