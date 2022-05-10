package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AttrController {
    @RequestMapping("attr")
    public String test(Model model) {
        model.addAttribute("action", "/user/update");
        model.addAttribute("text", "点我一下");
        model.addAttribute("method", "post");

        return "attr";
    }
}
