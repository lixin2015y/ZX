package com.lee.service;

import com.lee.api.CommentService;
import com.lee.constant.ZxException;
import com.lee.dao.CommentDao;
import com.lee.dao.MessageDao;
import com.lee.entity.Comment;
import com.lee.entity.Message;
import com.lee.entity.User;
import com.lee.utils.Utils;
import com.lee.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lee
 **/
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentDao commentDao;

    @Autowired
    MessageDao messageDao;

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
        //1、表示来自用户
        message.setFromtype("1");
        message.setToid(selectUserByEntity(comment).getId());
        //1、表示评论 2、表示回复
        message.setMessagetype(comment.getEntitytype());
        switch (message.getMessagetype()) {
            case "1":
                message.setEntityid(comment.getEntityid());
                break;
            case "2":
                message.setEntityid(commentDao.getNewsByCommentId(comment.getEntityid()));
                break;
            default:
                throw new ZxException("数据错误");
        }
        message.setTime(new Date());
        //1、代表已删除 0、代表未删除
        message.setDeleted("0");
        messageDao.addMessage(message);
    }


    @Override
    public void updateLike(String commentId, int like) throws ZxException {

        if (commentDao.updateLike(commentId, like) != 1) {
            throw new ZxException("修改数据错误");
        }
    }


    private User selectUserByEntity(Comment comment) throws ZxException {

        switch (comment.getEntitytype()) {
            case "1":
                return commentDao.getUserByNewsId(comment.getEntityid());
            case "2":
                return commentDao.getUserByCommentId(comment.getEntityid());
            default:
                throw new ZxException("错误错误");
        }
    }
}
