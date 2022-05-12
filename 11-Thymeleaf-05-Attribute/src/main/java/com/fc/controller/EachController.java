package com.fc.controller;

import com.fc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EachController {
    @RequestMapping("each")
    public String test(Model model) {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "易烊千玺", 22, "四个字"));
        users.add(new User(2, "迪丽热巴", 30, "四个字"));
        users.add(new User(3, "古力娜扎", 28, "四个字"));

        model.addAttribute("users", users);

        // 有序的hashMap，里面的元素插入的顺序和存储的顺序是一致的
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", 1);
        map.put("name", "易烊千玺");
        map.put("age", 22);
        map.put("gender", true);
        map.put("info", "四个字");

        model.addAttribute("map", map);

        Map<String, User> userMap = new LinkedHashMap<>();

        userMap.put("user1", new User(1, "易烊千玺", 22, "四个字"));
        userMap.put("user2", new User(2, "迪丽热巴", 30, "四个字"));
        userMap.put("user3", new User(3, "古力娜扎", 28, "四个字"));

        model.addAttribute("userMap", userMap);

        ArrayList<Map<String, User>> maps = new ArrayList<>();

        maps.add(userMap);

        Map<String, User> userMap2 = new LinkedHashMap<>();

        userMap2.put("user4", new User(4, "玛卡巴卡", 22, "四个字"));
        userMap2.put("user5", new User(5, "依古比古", 30, "四个字"));
        userMap2.put("user6", new User(6, "汤不里不", 28, "四个字"));
        maps.add(userMap2);

        Map<String, User> userMap3 = new LinkedHashMap<>();

        userMap3.put("user7", new User(7, "鱼香肉丝", 22, "四个字"));
        userMap3.put("user8", new User(8, "锅巴肉片", 30, "四个字"));
        userMap3.put("user9", new User(9, "宫保鸡丁", 28, "四个字"));
        maps.add(userMap3);

        model.addAttribute("maps", maps);

        String[] array = new String[3];
        array[0] = "易烊千玺";
        array[1] = "迪丽热巴";
        array[2] = "古力娜扎";

        model.addAttribute("array", array);

        return "each";
    }
}
