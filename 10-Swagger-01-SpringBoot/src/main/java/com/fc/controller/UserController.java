package com.fc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(value = "user", description = "用户的所有操作")
public class UserController {
    @ApiOperation("查询所有的用户")
    @GetMapping("findAll")
    public String findAll() {
        return "user findAll";
    }

    @ApiOperation("添加用户")
    @PostMapping("add")
    public String add(@ApiParam(name = "username", value = "用户名，必选") @RequestParam String username,
                      @ApiParam("用户密码，必选") @RequestParam String password) {
        return "user add:" + username + password;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("delete")
    public String delete() {
        return "user delete";
    }

    @ApiOperation("修改用户")
    @PutMapping("update")
    public String update() {
        return "user update";
    }
}
