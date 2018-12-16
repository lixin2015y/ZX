package com.lee.dao;

import com.lee.entity.News;
import com.lee.vo.UserNewsVo;

import java.util.List;

/**
 * @author lee
 **/
public interface NewsDao {

    int addNews(News news);

    List<UserNewsVo> selectUserNews(String userid);
}
