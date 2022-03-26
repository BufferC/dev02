package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("data")
public class DataController {
    @RequestMapping("getDate")
    public void getDate(Date date) {
        System.out.println(date);
    }
}
