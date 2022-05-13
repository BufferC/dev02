package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InlineController {
    @RequestMapping("inline")
    public String test(Model model) {
        model.addAttribute("html", "<h1 align='center'>易烊千玺</h1>");

        model.addAttribute("name", "易烊千玺");
        model.addAttribute("age", 22);
        model.addAttribute("gender", true);

        model.addAttribute("class", "class2");
        model.addAttribute("color", "green");
        model.addAttribute("align", "right");

        return "inline";
    }
}
