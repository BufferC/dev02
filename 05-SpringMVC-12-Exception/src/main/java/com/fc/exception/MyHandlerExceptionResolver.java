package com.fc.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 如果发生了异常都会执行resolveException方法
@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        if (ex instanceof SingletonDogException) {
            modelAndView.addObject("message", "两个黄鹂鸣翠柳，你还没有女朋友");
        } else {
            modelAndView.addObject("message", ex.getMessage());
        }

        modelAndView.setViewName("/error.jsp");

        return modelAndView;
    }
}
