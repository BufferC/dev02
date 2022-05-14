package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class ExpressionUtilityObjectsController {
    @RequestMapping("dates")
    public String testDates(Model model) {
        // 日期
        model.addAttribute("date", new Date());

        // 数组
        Date[] array = {new Date(120, 0, 1, 1, 1, 1),
                new Date(110, 6, 3, 12, 12, 12)};
        model.addAttribute("array", array);

        // list
        List<Date> list = new ArrayList<>();
        list.add(new Date(120, 1, 1, 1, 1, 1));
        list.add(new Date(120, 2, 2, 2, 2, 2));
        model.addAttribute("list", list);

        // map
        Set<Date> set = new HashSet<>();
        set.add(new Date(120, 1, 1, 1, 1, 1));
        set.add(new Date(120, 2, 2, 2, 2, 2));
        model.addAttribute("set", set);

        return "dates";
    }
}