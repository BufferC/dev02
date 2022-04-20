package com.fc.controller;

import com.fc.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestConfig testConfig;

    @RequestMapping("test")
    public String test() {
        System.out.println("hashcode：" + testConfig.hashCode());
        return testConfig.test();
    }
}
