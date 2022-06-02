package com.fc.controller;

import com.fc.annotation.Autowired;
import com.fc.annotation.Controller;
import com.fc.annotation.RequestMapping;
import com.fc.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public void findAll() {}
}
