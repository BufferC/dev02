package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

// 注意，这里不能使用@RestController注解，因为当前我们必须要使用视图解析器去解析视图
// 如果使用了@RestController注解将会跳过视图解析器
@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {
    @RequestMapping("test")
    public String test(HttpServletRequest request) {
        request.setAttribute("data", "请求域对象设置参数到Thymeleaf中");
        // 只要能够识别出来模板，就没有波浪线了
        return "index";
    }

    @RequestMapping("test1")
    public ModelAndView test1(ModelAndView mv) {
        mv.addObject("data", "ModelAndView设置参数到Thymeleaf中");
        // 只要能够识别出来模板，就没有波浪线了
        mv.setViewName("index");

        return mv;
    }

    // 推荐使用Model加字符串跳转的方式
    @RequestMapping("test2")
    public String test2(Model model) {
        model.addAttribute("data", "Model设置参数到Thymeleaf中");

        // 只要能够识别出来模板，就没有波浪线了
        return "index";
    }
}
