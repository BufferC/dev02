package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class BooleanController {
    @RequestMapping("boolean")
    public String test(Model model) {
        model.addAttribute("boolean", true);
        model.addAttribute("age", 22);

        // 这个集合很有可能是为null
        // 我们在查询数据库的时候，如果数据库中没有符合条件的数据
        // 那么获取到的将会是个null集
        ArrayList<String> notes = new ArrayList<>();

        notes.add("日记");

        model.addAttribute("notes", notes);

        return "boolean";
    }
}
