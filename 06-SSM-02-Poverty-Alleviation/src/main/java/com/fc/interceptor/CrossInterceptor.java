package com.fc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 跨域拦截器
public class CrossInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "DELETE,GET,POST,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Max-Age", "3600");

        // 如果是预检，直接返回就行了
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            return false;
        }

        return true;
    }
}
