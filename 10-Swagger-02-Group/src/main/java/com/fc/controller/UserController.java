package com.fc.controller;

import com.fc.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "用户模块", description = "用户的所有操作")
public class UserController {
    @ApiOperation(hidden = true, value = "查询所有的用户", tags = "select")
    @GetMapping("findAll")
    public String findAll() {
        return "user findAll";
    }

    @ApiOperation(value = "获取用户", tags = "select")
    @GetMapping("getUser")
    public User getUser() {
        return new User(1, "易烊千玺", "123456");
    }

    @ApiOperation(value = "根据id查询对应的用户", tags = "select")
    @GetMapping("findById")
    public String findById(@ApiParam(allowableValues = "range(1, 5)", required = true, hidden = true) @RequestParam(defaultValue = "1") Integer id) {
        System.out.println("获取到的id：" + id);
        return "user findById:" + id;
    }

    @ApiOperation(value = "分页查询用户", tags = "select")
    @GetMapping("findByPage")
    public String findByPage(@ApiParam(defaultValue = "分页参数：当前页", example = "1")
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                             @ApiParam(defaultValue = "分页参数：每页显示多少条数据", example = "5")
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        return "findByPage:当前页：" + pageNum + ",每页显示多少条数据：" + pageSize;
    }

    @ApiOperation(value = "添加用户", tags = "operator")
    @PostMapping("add")
    public String add(@ApiParam(name = "username", value = "用户名，必选", required = true) @RequestParam String username,
                      @ApiParam("用户密码，必选") @RequestParam String password) {
        return "user add:" + username + password;
    }

    @ApiOperation(value = "删除用户", tags = "operator")
    @DeleteMapping("delete")
    public String delete() {
        return "user delete";
    }

    @ApiOperation(value = "修改用户", tags = "operator")
    @PutMapping("update")
    public String update() {
        return "user update";
    }
}
