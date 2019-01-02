package com.lee.controller;

import com.lee.api.CommentService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.entity.Comment;
import com.lee.model.HostHolder;
import com.lee.util.ZxUtil;
import com.lee.utils.Utils;
import com.lee.vo.CommentVo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.Util;
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

    @RequestMapping(path = "getComment", method = RequestMethod.POST)
    ResponseMessage getComment(String newsId) {

        if (StringUtils.isBlank(newsId)) {
            return Result.error();
        }

        List<CommentVo> commentVoList = commentService.getComments(newsId);

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
}
