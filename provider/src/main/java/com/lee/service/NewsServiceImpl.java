package com.lee.service;

import com.lee.api.NewsService;
import com.lee.constant.ZxException;
import com.lee.dao.NewsDao;
import com.lee.entity.News;
import com.lee.vo.UserNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lee
 **/
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDao newsDao;

    public void addNews(News news) throws ZxException {
        if (newsDao.addNews(news) != 1) {
            throw new ZxException("添加数据失败");
        }
    }

    public List<UserNewsVo> selectUserNews(String userid) {
        return newsDao.selectUserNews(userid);
    }
}
