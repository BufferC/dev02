package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    @RequestMapping("url")
    public String testUrl() {
        return "url";
    }

    @RequestMapping("strings")
    public String testStrings(Model model) {
        model.addAttribute("user", new User(1, "易烊千玺", 21));
        model.addAttribute("text", "HelloWorld");

        return "strings";
    }

    @RequestMapping("nums")
    public String testNums() {
        return "nums";
    }
}
