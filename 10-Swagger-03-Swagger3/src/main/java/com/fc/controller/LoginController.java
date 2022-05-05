package com.fc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("getVerifyCode")
    public String getVerifyCode() {
        return "getVerifyCode";
    }

    @GetMapping("verifyUsername")
    public Boolean verifyUsername(@RequestParam String username) {
        return true;
    }
}
