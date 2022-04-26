package com.fc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("{username}/{password}")
    public String add(@PathVariable("username") String username,
                      @PathVariable("password") String password) {
        return "添加请求被执行" + username + password;
    }

    @GetMapping
    public String get() {
        return "查询请求被执行";
    }

    @PutMapping("{id}/{username}/{password}")
    public String update(@PathVariable("id") Integer id,
                         @PathVariable("username") String username,
                         @PathVariable("password") String password) {
        return "修改请求被执行" + id + username + password;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Integer id) {
        return "删除请求被执行" + id;
    }
}
