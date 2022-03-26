package com.fc.controller;

import com.fc.exception.SingletonDogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("exception")
public class ExceptionController {
    @RequestMapping("system")
    public void testSystem() {
        int num = 1 / 0;
    }

    @RequestMapping("custom")
    public void testCustom() throws SingletonDogException {
        throw new SingletonDogException("单身狗");
    }
}
