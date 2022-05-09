package com.fc.controller;

import com.fc.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("selective")
public class SelectiveExpressionController {
    @RequestMapping("test")
    public String testSelectiveExpression(Model model) {

        model.addAttribute("person", new Person(1, "易烊千玺", 22));

        return "selective";
    }

}
