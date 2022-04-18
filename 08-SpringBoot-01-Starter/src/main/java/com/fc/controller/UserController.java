package com.fc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("hello")
    public String hello() {
        return "鸡蛋、胡辣汤、酱香饼、韭菜盒子";
    }
}
