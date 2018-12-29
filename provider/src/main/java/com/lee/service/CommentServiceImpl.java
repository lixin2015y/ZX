package com.lee.service;

import com.lee.api.CommentService;
import com.lee.dao.CommentDao;
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
}
