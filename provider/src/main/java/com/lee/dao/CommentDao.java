package com.lee.dao;

import com.lee.vo.CommentVo;

import java.util.List;

/**
 * @author lee
 **/
public interface CommentDao {

    List<CommentVo> getCommentList(String newsId);
}
