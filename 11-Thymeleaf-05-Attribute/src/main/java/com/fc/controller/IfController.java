package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IfController {
    @RequestMapping("if")
    public String test(Model model) {
        model.addAttribute("string1", "");
        model.addAttribute("string2", null);
        model.addAttribute("string3", "null");
        return "if";
    }
}
