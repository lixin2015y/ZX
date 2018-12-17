package com.lee.dao;

import com.lee.entity.News;
import com.lee.vo.NewsPageVo;
import com.lee.vo.NewsVo;
import com.lee.vo.UserNewsVo;

import java.util.List;

/**
 * @author lee
 **/
public interface NewsDao {

    int addNews(News news);

    List<UserNewsVo> selectUserNews(String userid);

    List<NewsVo> getNews(NewsPageVo newsPageVo);

    Integer getNewsTotal(NewsPageVo newsPageVo);
}
