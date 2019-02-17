package com.lee.service;

import com.lee.api.CommentService;
import com.lee.constant.ZxException;
import com.lee.dao.CommentDao;
import com.lee.entity.Comment;
import com.lee.entity.Message;
import com.lee.entity.User;
import com.lee.utils.Utils;
import com.lee.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author lee
 **/
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentDao commentDao;


    @Override
    public List<CommentVo> getComments(String newsId) {

        return commentDao.getCommentList(newsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComment(Comment comment) throws ZxException {

        //添加回复
        if (commentDao.addComment(comment) != 1) {
            throw new ZxException("添加回复失败");
        }


        //系统发送消息
        Message message = new Message();
        message.setId(Utils.getUUID(32));
        message.setFromid(comment.getUserid());
        //表示由用户发起消息
        message.setFromtype("1");
    }


    @Override
    public void updateLike(String commentId, int like) throws ZxException {

        if (commentDao.updateLike(commentId, like) != 1) {
            throw new ZxException("修改数据错误");
        }
    }


    private User selectUserByEntity(Comment comment) {

        switch (comment.getEntityid()) {
            case "1":
                break;
            default:
                break;
        }
        return null;
    }
}
