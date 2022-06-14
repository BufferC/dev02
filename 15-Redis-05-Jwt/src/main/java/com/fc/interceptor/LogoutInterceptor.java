package com.fc.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogoutInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    // 方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 根据token获取到手机号，然后删除redis中的两个键
        DecodedJWT decode = JWT.decode(request.getParameter("token"));

        String phone = decode.getClaim("phone").asString();

        // 批量删除包含该手机号的所有key
        redisUtil.delKeys(phone);
    }
}
