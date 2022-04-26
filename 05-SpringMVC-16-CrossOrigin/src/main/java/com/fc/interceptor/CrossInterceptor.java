package com.fc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "DELETE,GET,POST,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");

        return false;
    }
}
