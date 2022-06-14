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
    public ResultVO getVerifyCode(@RequestParam String phone) {
        return userService.getVerifyCode(phone);
    }

    @RequestMapping("logout")
    public ResultVO logout(String token) {
        return userService.logout(token);
    }

    @RequestMapping("login")
    public ResultVO login(@RequestParam String phone,
                          String code) {
        ResultVO vo = new ResultVO();
        vo.setSuccess(false);
        vo.setCode(-3);

        // 对参数进行非空判断
        if (!StringUtils.hasLength(code)) {
            vo.setMessage("验证码不能为空！");
            return vo;
        }

        // 要求手机号不能为空，并且长度必须等于11
        if (!StringUtils.hasLength(phone) && phone.length() != 11) {
            vo.setMessage("手机号格式不正确！");
            return vo;
        }

        return userService.login(phone, code);
    }
}
