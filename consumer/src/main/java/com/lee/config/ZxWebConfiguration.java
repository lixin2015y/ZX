package com.lee.config;

import com.lee.interceptor.PassportInterceptor;
import com.lee.interceptor.UserInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 */
@Component
public class ZxWebConfiguration implements WebMvcConfigurer {

    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    UserInfoInterceptor userInfoInterceptor;


    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInfoInterceptor).addPathPatterns("/**");

        List<String> addPatterns = new ArrayList<String>();
        addPatterns.add("/html/jie/**");
        addPatterns.add("/html/user/**");
        addPatterns.add("/user/**");
        addPatterns.add("/news/addNews");
        addPatterns.add("/news/getUserNews");
        addPatterns.add("/comment/addComment");
        List<String> excludePatterns = new ArrayList<String>();
        excludePatterns.add("/html/user/login.html");
        excludePatterns.add("/html/user/reg.html");
        excludePatterns.add("/html/jie/detail.html");
        excludePatterns.add("/html/jie/index.html");
        registry.addInterceptor(passportInterceptor)
                .addPathPatterns(addPatterns)
                .excludePathPatterns(excludePatterns);
    }

    public void addViewControllers(ViewControllerRegistry registry) {
    }
}
