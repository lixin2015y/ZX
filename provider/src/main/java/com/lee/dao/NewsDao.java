package com.lee.dao;

import com.lee.entity.News;

import java.util.List;

/**
 * @author lee
 **/
public interface NewsDao {

    int addNews(News news);

    List<News> selectNews(String id);
}
