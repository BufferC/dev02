package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {
    @RequestMapping("getFoods")
    public ModelAndView getFoods(ModelAndView mv) {
        // 模拟连接数据库操作...

        System.out.println("连接数据库操作...");

        List<String> foods = new ArrayList<>();

        foods.add("烤盘饭");
        foods.add("老碗面");
        foods.add("麻辣烫");

        mv.addObject("foods", foods);

        mv.setViewName("/account/getFoods");
        return mv;
    }
}
