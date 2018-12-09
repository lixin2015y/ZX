package com.lee.interceptor;

import com.lee.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lee
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();

                }break;
            }
        }



        if (ticket != null) {
            logger.info("存在ticket");
            if (redisTemplate.hasKey(ticket)) {
                logger.info("redis中ticket匹配");
                ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
            } else {
                response.sendRedirect("/html/user/login.html");
                return false;
            }
        } else {
            logger.info("ticket不存在");
            response.sendRedirect("/html/user/login.html");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
