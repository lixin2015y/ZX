package com.lee.interceptor;

import com.lee.entity.User;
import com.lee.model.HostHolder;
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


    @Autowired
    HostHolder hostHolder;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();

        if (ticket != null) {
            System.out.println("ticket不为空");
            //查询redis验证ticket
            if (redisTemplate.hasKey(ticket)) {
                System.out.println("存在ticket");
                hostHolder.setUser(valueOperations.get(ticket));
            }
        } else {
            System.out.println("ticket为空");
            response.sendRedirect("/html/user/login.html");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getUser() != null) {
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
