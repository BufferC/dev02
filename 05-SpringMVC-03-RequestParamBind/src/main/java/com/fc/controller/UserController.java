package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("testParam")
    public String testParam(String name, Integer age, String gender, String info) {
        System.out.println(name + ":" + age + ":" + gender + ":" + info);

        return "/success.jsp";
    }

    @RequestMapping("testObject")
    public String testObject(User user) {
        System.out.println(user);

        return "/success.jsp";
    }
}
