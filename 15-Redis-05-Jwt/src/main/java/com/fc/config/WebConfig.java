package com.fc.config;

import com.fc.interceptor.GetVerifyCodeInterceptor;
import com.fc.interceptor.JwtInterceptor;
import com.fc.interceptor.LoginInterceptor;
import com.fc.interceptor.LogoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private LogoutInterceptor logoutInterceptor;
    @Autowired
    private GetVerifyCodeInterceptor getVerifyCodeInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 只拦截获取验证码的方法
        registry.addInterceptor(getVerifyCodeInterceptor)
                .addPathPatterns("/user/getVerifyCode");

        // 只拦截登录操作
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/login");

        // jwt验证拦截器，除了登录相关以及静态资源，其他全部都拦
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/user/getVerifyCode")
                .excludePathPatterns("/user/login")
                .addPathPatterns("/**");

        // 只对退出登录进行拦截
        registry.addInterceptor(logoutInterceptor)
                .addPathPatterns("/user/logout");
    }
}
