package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TextController {
    @RequestMapping("text")
    public String test(Model model) {
        model.addAttribute("value", "<h1>易烊千玺</h1>");
        model.addAttribute("character", "&gt;");
        return "text";
    }
}
