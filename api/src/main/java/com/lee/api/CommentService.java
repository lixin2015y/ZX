package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.entity.Comment;
import com.lee.vo.CommentVo;

import java.util.List;

/**
 * @author lee
 **/
public interface CommentService {

    List<CommentVo> getComments(String newsId);

    void addComment(Comment comment) throws ZxException;

    void updateLike(String commentId, int like) throws ZxException;

}
