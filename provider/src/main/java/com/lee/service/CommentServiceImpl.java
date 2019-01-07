package com.lee.service;

import com.lee.api.CommentService;
import com.lee.constant.ZxException;
import com.lee.dao.CommentDao;
import com.lee.entity.Comment;
import com.lee.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lee
 **/
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentDao commentDao;


    public List<CommentVo> getComments(String newsId) {

        return commentDao.getCommentList(newsId);
    }

    public void addComment(Comment comment) throws ZxException {
        if (commentDao.addComment(comment) != 1) {
            throw new ZxException("添加回复失败");
        }
    }


    public void updateLike(String commentId, int like) throws ZxException {

        if (commentDao.updateLike(commentId, like) != 1) {
            throw new ZxException("修改数据错误");
        }
    }
}
