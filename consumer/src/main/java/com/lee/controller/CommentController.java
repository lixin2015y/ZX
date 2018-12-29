package com.lee.controller;

import com.lee.api.CommentService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.vo.CommentVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lee
 **/
@RequestMapping("comment")
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(path = "/getComment", method = RequestMethod.POST)
    ResponseMessage getComment(String newsId) {

        if (StringUtils.isBlank(newsId)) {
            return Result.error();
        }

        List<CommentVo> commentVoList = commentService.getComments(newsId);

        return Result.success(commentVoList);
    }
}
