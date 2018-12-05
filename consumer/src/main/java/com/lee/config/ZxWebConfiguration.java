package com.lee.config;

import com.lee.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pattens = new ArrayList<String>();
        pattens.add("/html/user/login.html");
        pattens.add("/html/user/reg.html");
        pattens.add("/login/**");
        pattens.add("/res/**");
        registry.addInterceptor(passportInterceptor).addPathPatterns("/**").excludePathPatterns(pattens);
    }
}
