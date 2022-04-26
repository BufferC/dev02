package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("test")
    public String test(Model model) {
        model.addAttribute("username", "易烊千玺");

        return "index";
    }
}
