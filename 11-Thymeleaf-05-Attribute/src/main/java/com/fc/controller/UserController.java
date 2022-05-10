package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("action")
    public String test(Model model) {
        model.addAttribute("user", new User(1, "易烊千玺", 22, "四个子"));

        return "action";
    }

    @PostMapping("update")
    @ResponseBody
    public User update(User user) {
        return user;
    }
}
