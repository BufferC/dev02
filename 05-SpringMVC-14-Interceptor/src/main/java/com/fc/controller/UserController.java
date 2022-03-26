package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("toIndex")
    public String test() {
        System.out.println("controller执行");

        return "/success.jsp";
    }
}
