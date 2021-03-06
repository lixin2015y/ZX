package com.lee.interceptor;

import com.lee.api.UserService;
import com.lee.model.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lee
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (hostHolder.getUser() == null) {
            logger.info("未登录");
            response.sendRedirect("/html/user/login.html");
            return true;
        } else {
            logger.info("已登录");
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
