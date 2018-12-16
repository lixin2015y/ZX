package com.lee.controller;

import com.lee.api.NewsService;
import com.lee.constant.ResponseMessage;
import com.lee.constant.Result;
import com.lee.constant.ZxException;
import com.lee.entity.News;
import com.lee.model.HostHolder;
import com.lee.util.QiniuUtil;
import com.lee.util.ZxUtil;
import com.lee.utils.Utils;
import com.lee.vo.UserNewsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @version 1.0
 * @date 2018/12/13 18:27
 * @description TODO
 **/
@RequestMapping("news")
@RestController
public class NewsController {


    @Autowired
    HostHolder hostHolder;

    @Autowired
    NewsService newsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("addNews")
    ResponseMessage addNews(@RequestBody News news) {

        if (ZxUtil.isBlank(news.getNewstype(), news.getTitle(), news.getContent())) {
            return Result.error("必要参数为空");
        }

        news.setId(Utils.getUUID(32));
        news.setCreationtime(new Date());
        news.setUserid(hostHolder.getUser().getId());
        news.setTofine("0");
        news.setTotop("0");

        try {
            newsService.addNews(news);
        } catch (ZxException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success("添加成功");
    }

    @PostMapping("addNewsPicture")
    ResponseMessage addNewsPicture(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            String src = QiniuUtil.upload(bytes);
            Map<Object, Object> map = new HashMap(16);
            map.put("src", src + "-content_pic");
            return Result.success(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    @PostMapping("getUserNews")
    ResponseMessage getUserNews() {
        List<UserNewsVo> userNewsVoList = newsService.selectUserNews(hostHolder.getUser().getId());
        return Result.success(userNewsVoList);
    }

}
