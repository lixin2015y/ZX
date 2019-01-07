package com.lee.interceptor;

import com.lee.api.UserService;
import com.lee.entity.User;
import com.lee.model.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class UserInfoInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                }
                break;
            }
        }

        if (ticket != null) {
            logger.info("存在ticket");
            if (redisTemplate.hasKey(ticket)) {
                User user = userService.selectUserTotalInfoByTicket(ticket);
                if (user != null) {
                    hostHolder.setUser(user);
                } else {
                    logger.info("数据库未发现此数据");
                }
            } else {
                logger.info("缓存未发现此数据");
            }
        } else {
            logger.debug("ticket不存在");
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (hostHolder.getUser() != null) {
            hostHolder.clear();
        }
    }
}
