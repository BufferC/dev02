package com.fc.controller;

import com.fc.entity.Car;
import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("standard")
public class StandardExpressionController {
    @RequestMapping("test")
    public String testStandardExpression(Model model) {
        model.addAttribute("name", "易烊千玺");
        model.addAttribute("age", 22);
        model.addAttribute("married", false);

        User user = new User(1, "易烊千玺", 22, "男", "四个字", new Car("比亚迪", "白色"));

        model.addAttribute("user", user);
        return "standard";
    }

    @RequestMapping("test1")
    public String testStandardExpression1(HttpServletRequest request) {
        request.setAttribute("name", "迪丽热巴");
        request.setAttribute("age", 30);
        request.setAttribute("married", false);

        User user = new User(2, "迪丽热巴", 30, "女", "胖", new Car("比亚迪", "白色"));

        request.setAttribute("user", user);
        return "standard";
    }
}
