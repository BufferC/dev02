package com.fc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 自定义的拦截器
public class CustomInterceptor implements HandlerInterceptor {
    // 前置通知，在Controller执行前执行
    // 返回值代表是否被拦截
    // 返回值是true就对应放行，否则不放行，无法执行Controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器：Controller方法执行前执行");

        return true;
    }

    // 在Controller执行之后会执行此方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器：Controller方法执行后执行");
    }

    // 视图解析器渲染视图之后会执行此方法，一般常用于资源的释放
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器：页面渲染后执行");
    }
}
