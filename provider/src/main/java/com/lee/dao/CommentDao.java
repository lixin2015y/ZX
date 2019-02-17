package com.lee.dao;

import com.lee.entity.Comment;
import com.lee.entity.News;
import com.lee.entity.User;
import com.lee.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lee
 **/
public interface CommentDao {

    List<CommentVo> getCommentList(String newsId);

    int addComment(Comment comment);

    int updateLike(@Param("commentId") String commentId, @Param("like") int like);

    User getUserByNewsId(String newsId);

    User getUserByCommentId(String commentId);

    String getNewsByCommentId(String commentId);
}
