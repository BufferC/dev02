package com.fc.component;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// 自定义国际化解析器
//@Component("localeResolver")
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 从请求参数中获取语言
        String lang = request.getParameter("lang");

        // 从请求对象中获取区域对象
        Locale locale;

        // 判断是否为空
        if (lang != null && !lang.equals("")) {
            // 获取语言和地区
            String[] arr = lang.split("_");

            // 给区域对象赋值
            locale = new Locale(arr[0], arr[1]);
        } else {
            // 给区域对象赋值
            locale = new Locale("en", "US");
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}