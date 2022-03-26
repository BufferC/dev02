package com.fc.advice;

import com.fc.util.CustomFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@ControllerAdvice
public class DataBindAdvice {
    // 所有的请求在到达Controller之前都会执行此方法
    @InitBinder
    public void parseDate(WebDataBinder binder) {
        // 添加自定义的格式化器
        binder.addCustomFormatter(new CustomFormatter());
    }

    // InitBinder中的参数对应控制器方法入参中的参数
    // 类似@RequestParam，也是用来进行参数绑定的
    @InitBinder("student")
    public void bindStudent(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("student.");
    }

    @InitBinder("teacher")
    public void bindTeacher(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("teacher.");
    }


    // 指定映射的参数名
    @ModelAttribute("createDateTime")
    public Date getCreateDateTime() {
        System.out.println("Controller执行之前....");
        return new Date();
    }
}
