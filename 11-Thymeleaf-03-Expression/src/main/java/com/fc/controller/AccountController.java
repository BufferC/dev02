package com.fc.controller;

import com.fc.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("account")
public class AccountController {
    @RequestMapping("test")
    public String testAccount(Model model) {
        model.addAttribute("account", new Account(1, "易烊千玺", "123456"));
        return "url";
    }

    @RequestMapping("noParam")
    @ResponseBody
    public String testNoParam() {
        return "不带参数传递过来了";
    }

    @RequestMapping("getParam")
    @ResponseBody
    public Account testGetParam(Account account) {
        return account;
    }

    @RequestMapping("testRESTful/{id}/{username}/{password}")
    @ResponseBody
    public Account testRESTful(@PathVariable("id") Integer id,
                               @PathVariable("username") String username,
                               @PathVariable("password") String password) {
        return new Account(id, username, password);
    }
}
