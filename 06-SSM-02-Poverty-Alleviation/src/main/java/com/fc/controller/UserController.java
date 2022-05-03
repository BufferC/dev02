package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultVO login(@RequestParam String username,
                          @RequestParam String password) {
        return userService.login(username, password);
    }

    @GetMapping("getlist")
    public ResultVO getlist(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3")  Integer pageSize,
                            Long id) {
        return userService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("delete")
    public ResultVO delete(@RequestParam Long id) {
        return userService.delete(id);
    }
}
