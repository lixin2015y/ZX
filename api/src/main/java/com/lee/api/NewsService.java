package com.lee.api;

import com.lee.constant.ZxException;
import com.lee.entity.News;

import java.util.List;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/14 15:55
 * @description TODO
 **/
public interface NewsService {

    void addNews(News news) throws ZxException;

    List<News> selectNews(String id);

}
