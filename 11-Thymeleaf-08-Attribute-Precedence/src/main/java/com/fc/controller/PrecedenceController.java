package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PrecedenceController {
    @RequestMapping("precedence")
    public String test(Model model) {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "易烊千玺", "123456"));
        users.add(new User(2, "迪丽热巴", "123456"));
        users.add(new User(3, "玛卡巴卡", "123456"));

        model.addAttribute("users", users);

        Map<String, String> locations = new HashMap<>();

        locations.put("001", "安阳");
        locations.put("002", "濮阳");
        locations.put("003", "新乡");
        locations.put("005", "郑州");
        locations.put("006", "洛阳");
        locations.put("007", "开封");
        locations.put("008", "许昌");

        model.addAttribute("locations", locations);
        model.addAttribute("selected", "安阳");

        return "precedence";
    }
}
