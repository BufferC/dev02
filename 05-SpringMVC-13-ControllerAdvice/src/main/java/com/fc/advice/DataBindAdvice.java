package com.fc.advice;

import com.fc.util.CustomFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class DataBindAdvice {
    // 所有的请求在到达Controller之前都会执行此方法
    @InitBinder
    public void parseDate(WebDataBinder binder) {
        // 添加自定义的格式化器
        binder.addCustomFormatter(new CustomFormatter());
    }
}
