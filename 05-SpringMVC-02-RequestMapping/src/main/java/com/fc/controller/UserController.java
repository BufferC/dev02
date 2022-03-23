package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(path = {"register1", "register2", "register3"})
    public void getParam(String name, Integer age) {
        System.out.println(name + ":" + age);
    }
}
