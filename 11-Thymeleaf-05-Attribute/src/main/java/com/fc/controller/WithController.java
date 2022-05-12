package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WithController {
    @RequestMapping("with")
    public String with(Model model) {
        model.addAttribute("food", "鱼香肉丝");

        User[] users = new User[2];

        users[0] = new User(1, "易烊千玺", 22, "四个字");
        users[1] = new User(2, "迪丽热巴", 30, "四个字");

        model.addAttribute("users", users);

        return "with";
    }
}
