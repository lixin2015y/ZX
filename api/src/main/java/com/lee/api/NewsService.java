package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.entity.News;
import com.lee.vo.NewsDetailVo;
import com.lee.vo.NewsPageVo;
import com.lee.vo.NewsVo;
import com.lee.vo.UserNewsVo;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 **/
public interface NewsService {

    void addNews(News news) throws ZxException;

    List<UserNewsVo> selectUserNews(String id);

    Map<String, Object> getNews(NewsPageVo newsPageVo);

    List<NewsVo> getHotNews();

    NewsDetailVo getNewsDetail(String id);

}
