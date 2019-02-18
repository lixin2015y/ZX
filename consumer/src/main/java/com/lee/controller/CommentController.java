package com.lee.controller;

import com.lee.api.CommentService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.constant.ZxException;
import com.lee.entity.Comment;
import com.lee.entity.User;
import com.lee.model.HostHolder;
import com.lee.util.ZxUtil;
import com.lee.utils.Utils;
import com.lee.vo.CommentVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author lee
 **/
@RequestMapping("comment")
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    RedisTemplate redisTemplate;

    private String COMMENT = "comment:";

    @RequestMapping(path = "getComment", method = RequestMethod.POST)
    ResponseMessage getComment(String newsId) {

        if (StringUtils.isBlank(newsId)) {
            return Result.error();
        }

        User user = hostHolder.getUser();

        List<CommentVo> commentVoList = commentService.getComments(newsId);

        if (user != null) {
            for (CommentVo commentVo : commentVoList) {
                if (islike(commentVo.getId(), user.getId())) {
                    commentVo.setIslike("1");
                }
            }
        }

        return Result.success(commentVoList);
    }

    @PostMapping("addComment")
    ResponseMessage addComment(Comment comment) {

        if (ZxUtil.isBlank(comment.getEntityid(), comment.getEntitytype(), comment.getContent())) {
            return Result.error();
        }

        comment.setUserid(hostHolder.getUser().getId());
        comment.setId(Utils.getUUID(32));
        comment.setCreationtime(new Date());

        try {
            commentService.addComment(comment);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success("添加成功");
    }

    @PostMapping("like")
    ResponseMessage like(String commentId) {

        if (StringUtils.isBlank(commentId)) {
            return Result.error("参数为空");
        }

        User user = hostHolder.getUser();

        if (user == null) {
            return Result.error("9", "点赞需登录");
        }

        SetOperations set = redisTemplate.opsForSet();

        set.add(COMMENT + commentId, user.getId());

        try {
            commentService.updateLike(commentId, set.members(COMMENT + commentId).size());
        } catch (ZxException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

        return Result.success("点赞成功");
    }


    @PostMapping("dislike")
    ResponseMessage dislike(String commentId) {

        if (StringUtils.isBlank(commentId)) {
            return Result.error("参数为空");
        }

        User user = hostHolder.getUser();

        if (user == null) {
            return Result.error("9", "取消赞需登录");
        }

        SetOperations set = redisTemplate.opsForSet();

        set.remove(COMMENT + commentId, user.getId());

        try {
            commentService.updateLike(commentId, set.members(COMMENT + commentId).size());
        } catch (ZxException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }


    private Boolean islike(String commentId, String userId) {
        if (redisTemplate.opsForSet().isMember(COMMENT + commentId, userId)) {
            return true;
        }
        return false;
    }


}
