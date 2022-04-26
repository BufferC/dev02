package com.fc.controller;

import com.fc.util.MessageUtil;
import com.fc.vo.LoginVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("i18n")
public class I18nController {
    @RequestMapping("get")
    public LoginVO get() {
        LoginVO loginVO = new LoginVO();

        loginVO.setLogin(MessageUtil.get("login"));
        loginVO.setWelcome(MessageUtil.get("welcome"));
        loginVO.setTitle(MessageUtil.get("title"));
        loginVO.setUsername(MessageUtil.get("username"));
        loginVO.setPassword(MessageUtil.get("password"));
        loginVO.setRememberMe(MessageUtil.get("rememberMe"));

        return loginVO;
    }
}
