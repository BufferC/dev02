package com.fc.controller;

import com.fc.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("controller")
public class TestRestControllerAnnotationController {
    @RequestMapping("getUser")
    public User testUser() {
        return new User("易烊千玺", 22, "男", new Date(), "四个字");
    }
}
