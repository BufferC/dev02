package com.fc.controller;

import com.fc.exception.SingletonException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exception")
public class ExceptionController {

    @GetMapping("system")
    public String testSystemException() {
        int num = 1 / 0;

        return "没有问题";
    }

    @GetMapping("singleton")
    public void testSingletonException() throws SingletonException {
        throw new SingletonException("单身狗没女朋友");
    }
}
