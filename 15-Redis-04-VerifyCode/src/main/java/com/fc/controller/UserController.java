package com.fc.controller;

import com.fc.service.UserService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getVerifyCode")
    public ResultVO getVerifyCode(@RequestParam String username) {
        return userService.getVerifyCode(username);
    }

    @RequestMapping("login")
    public ResultVO login(@RequestParam String username,
                          @RequestParam String password,
                          String code) {
        ResultVO vo = new ResultVO();
        vo.setSuccess(false);
        vo.setCode(-3);

        // 对参数进行非空判断
        if (!StringUtils.hasLength(code)) {
            vo.setMessage("验证码不能为空！");
            return vo;
        }

        if (!StringUtils.hasLength(username)) {
            vo.setMessage("用户名不能为空！");
            return vo;
        }

        if (!StringUtils.hasLength(password)) {
            vo.setMessage("密码不能为空！");
            return vo;
        }

        return userService.login(username, password, code);
    }
}
