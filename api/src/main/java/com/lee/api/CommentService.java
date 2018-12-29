package com.lee.api;

import com.lee.vo.CommentVo;

import java.util.List;

/**
 * @author lee
 **/
public interface CommentService {

    List<CommentVo> getComments(String newsId);


}
