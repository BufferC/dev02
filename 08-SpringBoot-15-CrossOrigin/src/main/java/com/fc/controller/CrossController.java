package com.fc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cross")
//@CrossOrigin(maxAge = 3600,
//        origins = "http://127.0.0.1:8848",
//        allowCredentials = "true",
//methods = {RequestMethod.DELETE,
//        RequestMethod.GET,
//        RequestMethod.POST,
//        RequestMethod.PUT,
//        RequestMethod.OPTIONS})
public class CrossController {
    @GetMapping("test")
    public String test() {

        return "请求成功！";
    }
}
