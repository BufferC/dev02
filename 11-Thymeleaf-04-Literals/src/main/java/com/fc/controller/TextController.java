package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TextController {
    @RequestMapping("text")
    public String test(Model model) {
        model.addAttribute("name", "易烊千玺");
        model.addAttribute("info", "登录成功");
        return "text";
    }
}
